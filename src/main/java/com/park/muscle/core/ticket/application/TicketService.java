package com.park.muscle.core.ticket.application;

import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.member.application.MemberService;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.ticket.domain.TicketRepository;
import com.park.muscle.core.ticket.dto.TicketDto.TicketCreateResponse;
import com.park.muscle.core.ticket.dto.TicketDto.TicketResponse;
import com.park.muscle.core.ticket.dto.TicketDto.create;
import com.park.muscle.core.trainer.application.TrainerService;
import com.park.muscle.core.trainer.domain.Trainer;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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
        ticket.assignToMemberAndTrainer(ticket.getMember(), ticket.getTrainer());
        ticketRepository.save(ticket);
    }

    public Ticket findById(final Long ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        return ticket.orElse(null);
    }

    public List<Lesson> findAllLessonsByTicketId(final Long ticketId) {
        return ticketRepository.findAllLessonsByTicketId(ticketId);
    }

    public void saveTicket(final Ticket ticket) {
        ticketRepository.save(ticket);
    }
}
