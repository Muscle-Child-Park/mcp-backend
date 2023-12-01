package com.park.muscle.core.member.presentation;


import static com.park.muscle.core.trainer.dto.TrainerResponse.TrainerTicketInfoResponse;

import com.park.muscle.core.reservation.application.ReservationService;
import com.park.muscle.core.reservation.dto.ReservationRequest;
import com.park.muscle.core.ticket.application.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/member/reverse")
@Tag(name = "member-reservation")
public class MemberReservationController {
    private final ReservationService reservationService;
    private final TicketService ticketService;

    @Operation(summary = "Register Reservation", description = "Register a new reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PostMapping
    public void registerReservation(@RequestBody ReservationRequest.Create request) {
        reservationService.registerReservation(request);
    }

    @Operation(summary = "Get Trainer List by Member ID", description = "Register a new reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the trainer list"),
            @ApiResponse(responseCode = "404", description = "Trainer list not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping("/{memberId}")
    public ResponseEntity<List<TrainerTicketInfoResponse>> getTrainerList(@PathVariable Long memberId) {
        List<TrainerTicketInfoResponse> trainerTicketInfoResponse = ticketService.getTrainerReservations(memberId);
        return ResponseEntity.ok().body(trainerTicketInfoResponse);
    }
}
