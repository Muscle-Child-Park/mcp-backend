package com.park.muscle.core.ticket.presentation;

import com.park.muscle.core.member.application.MemberService;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.ticket.application.TicketService;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.ticket.dto.TicketDto;
import com.park.muscle.core.ticket.dto.TicketDto.LessonByTicketResponse;
import com.park.muscle.core.ticket.dto.TicketDto.TicketCreateResponse;
import com.park.muscle.core.ticket.dto.TicketDto.TrainerInfoByTicketResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Ticket Management", description = "Endpoints for managing tickets")
public class TicketController {
    private final TicketService ticketService;
    private final MemberService memberService;

    @Operation(summary = "Create a ticket", description = "Connected with trainer through unique tags and create ticket")
    @PostMapping("/ticket/request")
    public ResponseEntity<TicketCreateResponse> createTicket(@RequestBody TicketDto.create ticketCreateDto) {
        Member member = memberService.findMemberById(ticketCreateDto.getMemberId());
        TicketCreateResponse ticketCreateResponse = ticketService.createTicket(member, ticketCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ticketCreateResponse);
    }

    @Operation(summary = "Accept a ticket", description = "Accept a ticket with a specific ID")
    @PostMapping("/ticket/accept")
    public ResponseEntity<Void> acceptTicket(@RequestParam Long ticketId) {
        ticketService.acceptTicket(ticketId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get lessons by ticket ID", description = "Retrieve all lessons associated with a ticket")
    @GetMapping("/{ticketId}/lesson")
    public ResponseEntity<List<LessonByTicketResponse>> getLessonsByTicketId(@PathVariable Long ticketId) {
        Ticket ticket = ticketService.findById(ticketId);
        List<LessonByTicketResponse> lessons = ticketService.findAllLessonsByTicket(ticket);
        return ResponseEntity.status(HttpStatus.OK).body(lessons);
    }

    @Operation(summary = "trainers by ticket info", description = "response trainer information is available and names")
    @GetMapping("/{memberId}/trainers")
    public ResponseEntity<List<TrainerInfoByTicketResponse>> getTrainers(@PathVariable long memberId) {
        List<TrainerInfoByTicketResponse> allTicketByMemberId = ticketService.findAllTicketByMemberId(memberId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(allTicketByMemberId);
    }
}
