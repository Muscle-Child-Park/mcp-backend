package com.park.muscle.core.member.presentation;

import com.park.muscle.core.member.application.MemberService;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.member.dto.response.MemberResponse.HomeResponse;
import com.park.muscle.core.ticket.application.TicketService;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.ticket.dto.response.TicketResponse.LessonByTicketSimpleResponse;
import com.park.muscle.core.trainer.dto.TrainerResponse.TrainerTicketInfoResponse;
import com.park.muscle.global.response.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/member/home")
@Tag(name = "Member-home Management")
public class MemberHomeController {
    private final MemberService memberService;
    private final TicketService ticketService;

    @Operation(summary = "Member home", description = "Trainer, Reservation and log information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "홈 페이지 접근 성공"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping("/{memberId}")
    public ResponseEntity<DataResponse<HomeResponse>> getMemberHome(@PathVariable final long memberId) {
        Member member = memberService.findMemberById(memberId);
        List<Ticket> tickets = member.getTickets();
        List<TrainerTicketInfoResponse> trainerReservations = ticketService.getTrainerReservations(memberId);
        List<LessonByTicketSimpleResponse> flattenedList = tickets.stream()
                .flatMap(ticket -> ticketService.findAllSimpleLessonsByTicket(ticket).stream())
                .collect(Collectors.toList());

        if (hasValidTicketAndLessonContent(trainerReservations, flattenedList)) {
            return ResponseEntity.noContent().build();
        }
        HomeResponse homeResponse = HomeResponse.fromEntity(trainerReservations, flattenedList);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "멤버 홈 페이지 접근 성공", homeResponse), HttpStatus.OK);
    }

    private static boolean hasValidTicketAndLessonContent(final List<TrainerTicketInfoResponse> trainerReservations,
                                                          final List<LessonByTicketSimpleResponse> flattenedList) {
        return trainerReservations.isEmpty() && flattenedList.isEmpty();
    }
}
