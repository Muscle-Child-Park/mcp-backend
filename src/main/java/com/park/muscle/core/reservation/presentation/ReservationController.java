package com.park.muscle.core.reservation.presentation;


import static com.park.muscle.core.trainer.dto.TrainerResponse.TrainerTicketInfoResponse;

import com.park.muscle.core.reservation.application.ReservationService;
import com.park.muscle.core.reservation.dto.ReservationRequest;
import com.park.muscle.core.ticket.application.TicketService;
import com.park.muscle.global.response.DataResponse;
import com.park.muscle.global.response.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/reserve")
@Tag(name = "reservation controller")
public class ReservationController {
    private final ReservationService reservationService;
    private final TicketService ticketService;

    @Operation(summary = "Register Reservation", description = "Register a new reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PostMapping("/member")
    public ResponseEntity<MessageResponse> registerReservation(@Valid @RequestBody ReservationRequest.Create request) {
        log.info("reservation request = {}", request);
        log.info("reservation request.ticket = {}", request.getTicketId());
        reservationService.registerReservation(request);
        return new ResponseEntity<>(MessageResponse.of(HttpStatus.OK, "예약 생성 성공"), HttpStatus.OK);
    }

    @Operation(summary = "Get Trainer List by Member ID", description = "멤버 -> 트레이너 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the trainer list"),
            @ApiResponse(responseCode = "404", description = "Trainer list not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping("/member/{memberId}")
    public ResponseEntity<DataResponse<List<TrainerTicketInfoResponse>>> getTrainerList(@PathVariable Long memberId) {
        List<TrainerTicketInfoResponse> trainerTicketInfoResponse = ticketService.getTrainerReservations(memberId);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "트레이너 목록 조회 성공", trainerTicketInfoResponse),
                HttpStatus.OK);
    }
}
