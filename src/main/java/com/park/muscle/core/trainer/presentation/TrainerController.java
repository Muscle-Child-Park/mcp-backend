package com.park.muscle.core.trainer.presentation;

import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.ticket.dto.TicketDto.TrainerTicketResponse;
import com.park.muscle.core.trainer.application.TrainerService;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.core.trainer.dto.TrainerDto.LoginRequest;
import com.park.muscle.core.trainer.dto.TrainerDto.LoginResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
public class TrainerController {

    private final TrainerService trainerService;

    @ApiOperation(value = "트레이너 등록 또는 로그인", notes = "트레이너의 로그인 시도를 수행하고, 기존 회원이 아닌 경우 회원 가입을 완료하며 고유 아이디를 발급합니다.", response = Trainer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "트레이너 로그인 또는 가입 및 아이디 발급 성공"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> loginTrainer(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = trainerService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loginResponse);
    }

    @GetMapping("/{trainerId}/tickets")
    public ResponseEntity<List<TrainerTicketResponse>> getTrainerTickets(
            @ApiParam(value = "트레이너 ID", required = true) @PathVariable Long trainerId) {
        Trainer trainer = trainerService.getTrainerById(trainerId);
        List<Ticket> tickets = trainer.getTickets();
        List<TrainerTicketResponse> trainerTicketResponse = trainerService.getTrainerTickets(tickets);
        return ResponseEntity.ok(trainerTicketResponse);
    }

    @ApiOperation(value = "트레이너 프로필 업데이트", response = Trainer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "트레이너 프로필 업데이트 성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "트레이너를 찾을 수 없음")
    })
    @PutMapping("/profile/{trainerId}")
    public ResponseEntity<Trainer> updateTrainerProfile(
            @ApiParam(value = "트레이너 ID", required = true) @PathVariable Long trainerId,
            @ApiParam(value = "업데이트된 트레이너 프로필 정보", required = true) @RequestBody Trainer updatedTrainer) {

        return ResponseEntity.ok(updatedTrainer);
    }

    @ApiOperation(value = "트레이너 계정 삭제")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "트레이너 계정 삭제 성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "트레이너를 찾을 수 없음")
    })
    @DeleteMapping("auth/delete/{trainerId}")
    public ResponseEntity<Void> deleteTrainerAccount(
            @ApiParam(value = "트레이너 ID", required = true) @PathVariable Long trainerId) {
        // 삭제가 성공한 경우 204 No Content 반환
        return ResponseEntity.noContent().build();
    }
}
