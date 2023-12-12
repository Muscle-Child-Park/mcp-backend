package com.park.muscle.core.ticket.presentation;

import com.park.muscle.core.ticket.application.TicketService;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.ticket.dto.request.TicketRequest;
import com.park.muscle.core.ticket.dto.response.TicketResponse.LessonByTicketResponse;
import com.park.muscle.core.ticket.dto.response.TicketResponse.TicketBasicResponse;
import com.park.muscle.core.ticket.dto.response.TicketResponse.TrainerInfoByTicketResponse;
import com.park.muscle.core.trainer.application.TrainerService;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.global.response.DataResponse;
import com.park.muscle.global.response.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Ticket Management", description = "Endpoints for managing tickets")
public class TicketController {
    private final TicketService ticketService;
    private final TrainerService trainerService;

    @Operation(summary = "LessonCreate a ticket", description = "Connected with trainer through Member unique tags and Create ticket")
    @PostMapping("/request")
    public ResponseEntity<DataResponse<TicketBasicResponse>> createTicket(@Valid @RequestBody TicketRequest.Create ticketCreateDto) {
        Trainer trainer = trainerService.findTrainerById(ticketCreateDto.getTrainerId());
        TicketBasicResponse ticket = ticketService.createTicket(trainer, ticketCreateDto);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.CREATED, "멤버 유니크 태그를 통해 티켓 생성에 성공했습니다.", ticket),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Accept a ticket", description = "Accept a ticket with a specific ID")
    @PostMapping("/accept/{ticketId}")
    public ResponseEntity<MessageResponse> acceptTicket(@PathVariable Long ticketId) {
        ticketService.acceptTicket(ticketId);
        return new ResponseEntity<>(MessageResponse.of(HttpStatus.OK, "트레이너로부터 티켓이 수락 되었습니다."), HttpStatus.OK);
    }

    @Operation(summary = "Get lessons by ticket ID", description = "Retrieve all lessons associated with a ticket")
    @GetMapping("/lesson/{ticketId}")
    public ResponseEntity<DataResponse<List<LessonByTicketResponse>>> getLessonsByTicketId(@PathVariable Long ticketId) {
        Ticket ticket = ticketService.findTicketById(ticketId);
        List<LessonByTicketResponse> lessons = ticketService.findAllLessonsByTicket(ticket);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "수업 조회에 성공했습니다.", lessons), HttpStatus.OK);
    }

    @Operation(summary = "trainers by ticket info", description = "response trainer information is available and names")
    @GetMapping("/trainers/{memberId}")
    public ResponseEntity<DataResponse<List<TrainerInfoByTicketResponse>>> getTrainers(@PathVariable long memberId) {
        List<TrainerInfoByTicketResponse> allTicketByMemberId = ticketService.findAllTicketByMemberId(memberId);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "트레이너 정보 조회에 성공했습니다.", allTicketByMemberId),
                HttpStatus.OK);
    }
}
