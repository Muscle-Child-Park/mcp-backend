package com.park.muscle.core.trainer.presentation;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.reservation.dto.ReservationResponse.ReservationInfoResponse;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.ticket.dto.TicketDto.PendingMemberNameResponse;
import com.park.muscle.core.ticket.dto.TicketDto.TrainerTicketResponse;
import com.park.muscle.core.trainer.application.TrainerService;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.core.trainer.dto.TrainerRequestDto.LoginRequest;
import com.park.muscle.core.trainer.dto.TrainerResponseDto.LoginResponse;
import com.park.muscle.core.trainer.dto.TrainerResponseDto.TrainerHomeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/trainers")
@RequiredArgsConstructor
@RestController
@Tag(name = "Trainer Management", description = "Endpoints for managing trainer")
public class TrainerController {
    private final TrainerService trainerService;


    @Operation(summary = "트레이너 등록 또는 로그인", description = "트레이너의 로그인 시도를 수행하고, 기존 회원이 아닌 경우 회원 가입을 완료하며 고유 아이디를 발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "트레이너 로그인 또는 가입 및 아이디 발급 성공"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> loginTrainer(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = trainerService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loginResponse);
    }

    @Operation(summary = "등록된 티켓을 가져옵니다.", description = "회원으로부터 요청된 티켓 목록을 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "티켓 조회 성공"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping("/{trainerId}/tickets")
    public ResponseEntity<List<TrainerTicketResponse>> getTrainerTickets(@PathVariable Long trainerId) {
        Trainer trainer = trainerService.getTrainerById(trainerId);
        List<Ticket> tickets = trainer.getTickets();
        List<TrainerTicketResponse> trainerTicketResponse = trainerService.getTrainerTickets(tickets);
        return ResponseEntity.ok(trainerTicketResponse);
    }


    @Operation(summary = "트레이너 프로필 업데이트")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "트레이너 프로필 업데이트 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "404", description = "트레이너를 찾을 수 없음")
    })
    @PutMapping("/profile/{trainerId}")
    public ResponseEntity<Trainer> updateTrainerProfile(@PathVariable Long trainerId,
                                                        @RequestBody Trainer updatedTrainer) {

        /**TODO: 2023-11-23, 목, 23:33  -JEON
         *  TASK: 작업 해야함
         */
        return ResponseEntity.ok(updatedTrainer);
    }


    @Operation(summary = "트레이너 계정 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "트레이너 계정 삭제 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "404", description = "트레이너를 찾을 수 없음")
    })
    @DeleteMapping("auth/delete/{trainerId}")
    public ResponseEntity<Void> deleteTrainerAccount(@PathVariable Long trainerId) {
        trainerService.deleteTrainerAccount(trainerId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "트레이너 홈 화면 정보")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "트레이너 정보 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "404", description = "트레이너를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류 발생")
    })
    @GetMapping("/{trainerId}/members")
    public ResponseEntity<TrainerHomeResponse> trainerHome(@PathVariable Long trainerId) {
        Trainer trainer = trainerService.getTrainerById(trainerId);
        List<Ticket> tickets = trainer.getTickets();
        if (tickets == null) {
            return ResponseEntity.noContent().build();
        }
        List<Member> pendingMembers = trainerService.findPendingMembers(tickets);
        PendingMemberNameResponse pendingMembersResponse = trainerService.getPendingMembers(pendingMembers);
        List<ReservationInfoResponse> reserveMembers = trainerService.getReserveMembers(tickets);

        return ResponseEntity.ok().body(TrainerHomeResponse.fromEntity(pendingMembersResponse, reserveMembers));
    }
}
