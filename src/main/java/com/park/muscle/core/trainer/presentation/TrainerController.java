package com.park.muscle.core.trainer.presentation;

import static com.park.muscle.core.trainer.dto.TrainerRequest.DayOffRequest;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.reservation.dto.ReservationResponse.ReservationInfoResponse;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.ticket.dto.response.TicketResponse.PendingMemberNameResponse;
import com.park.muscle.core.ticket.dto.response.TicketResponse.TicketTrainerResponse;
import com.park.muscle.core.trainer.application.TrainerService;
import com.park.muscle.core.trainer.domain.Gym;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.core.trainer.dto.TrainerRequest.GymRequest;
import com.park.muscle.core.trainer.dto.TrainerRequest.LoginRequest;
import com.park.muscle.core.trainer.dto.TrainerResponse.LoginResponse;
import com.park.muscle.core.trainer.dto.TrainerResponse.TrainerHomeResponse;
import com.park.muscle.global.response.DataResponse;
import com.park.muscle.global.response.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/trainers")
@RequiredArgsConstructor
@RestController
@Tag(name = "Trainer Management", description = "Endpoints for managing trainer")
public class TrainerController {
    private final TrainerService trainerService;

    @Operation(summary = "트레이너 홈 화면 정보")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "트레이너 홈 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "404", description = "트레이너를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류 발생")
    })
    @GetMapping("/{trainerId}")
    public ResponseEntity<DataResponse<TrainerHomeResponse>> trainerHome(@PathVariable Long trainerId) {
        Trainer trainer = trainerService.findTrainerById(trainerId);
        List<Ticket> tickets = trainer.getTickets();
        if (tickets == null) {
            return ResponseEntity.noContent().build();
        }
        List<Member> pendingMembers = trainerService.findPendingMembers(tickets);
        PendingMemberNameResponse pendingMembersResponse = trainerService.getPendingMembers(pendingMembers);
        List<ReservationInfoResponse> reserveMembers = trainerService.getReserveMembers(tickets);
        TrainerHomeResponse trainerHomeResponse = TrainerHomeResponse.fromEntity(pendingMembersResponse,
                reserveMembers);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "트레이너 홈 조회 성공", trainerHomeResponse), HttpStatus.OK);
    }

    @Operation(summary = "트레이너 등록 또는 로그인", description = "트레이너의 로그인 시도를 수행하고, 기존 회원이 아닌 경우 회원 가입을 완료하며 고유 아이디를 발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "트레이너 로그인 또는 가입 및 아이디 발급 성공"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/auth/login")
    public ResponseEntity<DataResponse<LoginResponse>> loginTrainer(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = trainerService.login(loginRequest);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.CREATED, "트레이너 로그인 성공", loginResponse), HttpStatus.CREATED);
    }

    @Operation(summary = "등록된 티켓을 가져옵니다.", description = "회원으로부터 요청된 티켓 목록을 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "티켓 조회 성공"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping("/tickets/{trainerId}")
    public ResponseEntity<DataResponse<List<TicketTrainerResponse>>> getTrainerTickets(@PathVariable Long trainerId) {
        Trainer trainer = trainerService.findTrainerById(trainerId);
        List<Ticket> tickets = trainer.getTickets();
        List<TicketTrainerResponse> ticketTrainerResponse = trainerService.getTrainerTickets(tickets);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "티켓 조회 성공", ticketTrainerResponse), HttpStatus.OK);
    }

    @Operation(summary = "트레이너 프로필 GYM 업데이트")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "트레이너 프로필 업데이트 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "404", description = "트레이너를 찾을 수 없음")
    })
    @PostMapping("/profile/{trainerId}")
    public ResponseEntity<MessageResponse> addTrainerGym(@PathVariable Long trainerId,
                                                         @Valid @RequestBody GymRequest gymRequest) {
        Trainer trainer = trainerService.findTrainerById(trainerId);
        Gym gym = gymRequest.toEntity(gymRequest.getName());
        trainer.addGym(gym);
        trainerService.saveGym(trainer, gym);
        return new ResponseEntity<>(MessageResponse.of(HttpStatus.OK, "트레이너 프로필 업데이트 성공"), HttpStatus.OK);
    }

    @Operation(summary = "트레이너 휴무일 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "트레이너 휴무일 생성 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "404", description = "트레이너를 찾을 수 없음")
    })
    @PostMapping("/dayoff/{trainerId}")
    public ResponseEntity<MessageResponse> addTrainerOff(@PathVariable Long trainerId,
                                              @Valid @RequestBody List<DayOffRequest> dayOffRequest) {
        Trainer trainer = trainerService.findTrainerById(trainerId);
        trainerService.addTrainerOff(trainer, dayOffRequest);
        return new ResponseEntity<>(MessageResponse.of(HttpStatus.CREATED, "트레이너 휴무일 생성 성공"), HttpStatus.CREATED);
    }

    @Operation(summary = "트레이너 계정 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "트레이너 계정 삭제 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "404", description = "트레이너를 찾을 수 없음")
    })
    @DeleteMapping("auth/delete/{trainerId}")
    public ResponseEntity<MessageResponse> deleteTrainerAccount(@PathVariable Long trainerId) {
        trainerService.deleteTrainerAccount(trainerId);
        return new ResponseEntity<>(MessageResponse.of(HttpStatus.OK, "트레이너 계정 삭제 성공"), HttpStatus.OK);
    }
}
