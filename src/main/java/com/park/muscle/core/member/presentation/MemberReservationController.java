package com.park.muscle.core.member.presentation;


import static com.park.muscle.core.trainer.dto.TrainerResponseDto.FindResponse;

import com.park.muscle.core.reservation.application.ReservationService;
import com.park.muscle.core.reservation.dto.ReservationRequest;
import com.park.muscle.core.ticket.application.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = "Member-reservation Management")
public class MemberReservationController {
    private final ReservationService reservationService;
    private final TicketService ticketService;

    @PostMapping
    @ApiOperation(value = "Register Reservation", notes = "Register a new reservation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation registered successfully"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public void registerReservation(@RequestBody ReservationRequest.Create request) {
        reservationService.registerReservation(request);
    }

    @GetMapping("/{memberId}")
    @ApiOperation(value = "Get Trainer List by Member ID", response = FindResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the trainer list"),
            @ApiResponse(code = 404, message = "Trainer list not found")
    })
    public ResponseEntity<List<FindResponse>> getTrainerList(@PathVariable Long memberId) {
        List<FindResponse> findResponses = ticketService.getTrainerReservations(memberId);
        return ResponseEntity.ok().body(findResponses);
    }
}
