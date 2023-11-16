package com.park.muscle.core.ticket.presentation;

import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.ticket.application.TicketService;
import com.park.muscle.core.ticket.dto.TicketDto;
import com.park.muscle.core.ticket.dto.TicketDto.TicketCreateResponse;
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
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/ticket/request")
    public ResponseEntity<TicketCreateResponse> createTicket(@RequestBody TicketDto.create ticketCreateDto) {
        TicketCreateResponse ticketCreateResponse = ticketService.createTicket(ticketCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ticketCreateResponse);
    }

    @PostMapping("/ticket/accept")
    public ResponseEntity<Void> acceptTicket(@RequestParam Long ticketId) {
        ticketService.acceptTicket(ticketId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{ticketId}/lesson")
    public ResponseEntity<List<Lesson>> getLessonsByTicketId(@PathVariable Long ticketId) {
        List<Lesson> lessons = ticketService.findAllLessonsByTicketId(ticketId);
        return ResponseEntity.status(HttpStatus.OK).body(lessons);
    }
}
