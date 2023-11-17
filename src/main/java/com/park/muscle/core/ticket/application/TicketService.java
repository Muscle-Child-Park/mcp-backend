package com.park.muscle.core.ticket.application;

import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.ticket.domain.TicketRepository;
import com.park.muscle.core.ticket.dto.TicketDto.TicketCreateResponse;
import com.park.muscle.core.ticket.dto.TicketDto.TicketResponse;
import com.park.muscle.core.ticket.dto.TicketDto.create;
import com.park.muscle.core.trainer.application.TrainerService;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.core.trainer.dto.TrainerResponseDto.FindResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {
    private final TrainerService trainerService;
    private final TicketRepository ticketRepository;

    public TicketCreateResponse createTicket(final Member member, final create ticketCreateDto) {
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

    public List<Trainer> findAllTrainerByMemberId(final Long memberId) {
        List<Ticket> allTicketByMemberId = ticketRepository.findAllTicketByMemberId(memberId);
        List<Trainer> trainers = new LinkedList<>();
        for (Ticket ticket : allTicketByMemberId) {
            trainers.add(ticket.getTrainer());
        }
        return trainers;
    }

    public List<FindResponse> getTrainerReservations(final Long memberId) {
        List<Trainer> trainers = findAllTrainerByMemberId(memberId);
        List<Ticket> tickets = ticketRepository.findAllTicketByMemberId(memberId);

        Map<Long, Trainer> trainerMap = trainers.stream()
                .collect(Collectors.toMap(Trainer::getId, Function.identity()));

        return tickets.stream()
                .filter(ticket -> trainerMap.containsKey(ticket.getTrainer().getId()))
                .map(ticket -> {
                    Trainer trainer = trainerMap.get(ticket.getTrainer().getId());
                    return FindResponse.builder()
                            .trainerId(trainer.getId())
                            .name(trainer.getName())
                            .ticketGenerateInfo(ticket.getCreatedDate())
                            .totalQuantity(ticket.getTotalQuantity())
                            .leftQuantity(ticket.getLeftQuantity())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
