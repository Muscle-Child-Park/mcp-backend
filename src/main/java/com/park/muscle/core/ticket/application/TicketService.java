package com.park.muscle.core.ticket.application;

import com.park.muscle.core.member.application.MemberService;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.ticket.domain.TicketRepository;
import com.park.muscle.core.ticket.dto.TicketDto.TicketCreateResponse;
import com.park.muscle.core.ticket.dto.TicketDto.TicketResponse;
import com.park.muscle.core.ticket.dto.TicketDto.create;
import com.park.muscle.core.trainer.application.TrainerService;
import com.park.muscle.core.trainer.domain.Trainer;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final MemberService memberService;
    private final TrainerService trainerService;
    private final TicketRepository ticketRepository;

    public TicketCreateResponse createTicket(final create ticketCreateDto) {
        Member member = memberService.getMemberById(ticketCreateDto.getMemberId());
        Trainer trainer = trainerService.getTrainerById(ticketCreateDto.getTrainerId());
        Ticket ticket = ticketCreateDto.toEntity(member, trainer);
        ticketRepository.save(ticket);
        TicketResponse ticketResponse = new TicketResponse(member, trainer, ticket);
        return new TicketCreateResponse(ticketResponse);
    }

    public void acceptTicket(final Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
              .orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
        ticket.accept();
        ticketRepository.save(ticket);
    }

    public Ticket findById(final Long ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        return ticket.orElse(null);
    }
}
