package com.park.muscle.core.ticket.application;

import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.ticket.domain.TicketRepository;
import com.park.muscle.core.ticket.dto.request.TicketRequest.Create;
import com.park.muscle.core.ticket.dto.response.TicketResponse.LessonByTicketResponse;
import com.park.muscle.core.ticket.dto.response.TicketResponse.LessonByTicketSimpleResponse;
import com.park.muscle.core.ticket.dto.response.TicketResponse.TicketBasicResponse;
import com.park.muscle.core.ticket.dto.response.TicketResponse.TrainerInfoByTicketResponse;
import com.park.muscle.core.ticket.exception.TicketNotFoundException;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.core.trainer.dto.TrainerResponse.TrainerTicketInfoResponse;
import com.park.muscle.core.uniquetag.UniqueTagService;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {
    private final UniqueTagService uniqueTageService;
    private final TicketRepository ticketRepository;

    @Transactional
    public TicketBasicResponse createTicket(final Member member, final Create ticketCreateDto) {
        Trainer trainer = uniqueTageService.getTrainerByTagId(ticketCreateDto.getTrainerTagId());
        Ticket ticket = ticketCreateDto.toEntity(member, trainer);
        ticketRepository.save(ticket);
        return TicketBasicResponse.fromEntity(member, trainer, ticket);
    }

    @Transactional
    public void acceptTicket(final Long ticketId) {
        Ticket ticket = findTicketById(ticketId);
        ticket.assignToMemberAndTrainer(ticket.getMember(), ticket.getTrainer());
        ticketRepository.save(ticket);
    }

    public Ticket findTicketById(final Long ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        return ticket.orElseThrow(TicketNotFoundException::new);
    }

    public List<LessonByTicketResponse> findAllLessonsByTicket(final Ticket ticket) {
        List<Lesson> lesson = ticket.getLesson();
        return lesson.stream()
                .map(LessonByTicketResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public List<LessonByTicketSimpleResponse> findAllSimpleLessonsByTicket(final Ticket ticket) {
        List<Lesson> lesson = ticket.getLesson();
        return lesson.stream()
                .map(LessonByTicketSimpleResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public void saveTicket(final Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Transactional
    public List<Trainer> findAllTrainerByMemberId(final Long memberId) {
        List<Ticket> allTicketByMemberId = ticketRepository.findAllTicketByMemberId(memberId);
        List<Trainer> trainers = new LinkedList<>();
        for (Ticket ticket : allTicketByMemberId) {
            trainers.add(ticket.getTrainer());
        }
        return trainers;
    }

    @Transactional
    public List<Member> findAllMemberByTrainerId(final Long trainerId) {
        List<Ticket> allTicketByTrainerId = ticketRepository.findAllTicketByTrainerId(trainerId);
        List<Member> members = new LinkedList<>();
        for (Ticket ticket : allTicketByTrainerId) {
            members.add(ticket.getMember());
        }
        return members;
    }

    @Transactional
    public List<TrainerTicketInfoResponse> getTrainerReservations(final Long memberId) {
        List<Trainer> trainers = findAllTrainerByMemberId(memberId);
        List<Ticket> tickets = ticketRepository.findAllTicketByMemberId(memberId);

        Map<Long, Trainer> trainerMap = trainers.stream()
                .collect(Collectors.toMap(Trainer::getId, Function.identity()));

        return tickets.stream()
                .filter(ticket -> trainerMap.containsKey(ticket.getTrainer().getId()))
                .map(ticket -> {
                    Trainer trainer = trainerMap.get(ticket.getTrainer().getId());
                    return TrainerTicketInfoResponse.builder()
                            .trainerId(trainer.getId())
                            .name(trainer.getName())
                            .ticketGenerateInfo(ticket.getCreatedDate())
                            .totalQuantity(ticket.getTotalQuantity())
                            .leftQuantity(ticket.getLeftQuantity())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public List<TrainerInfoByTicketResponse> findAllTicketByMemberId(final long memberId) {
        List<Ticket> allTicketByMember = ticketRepository.findAllTicketByMemberId(memberId);
        return allTicketByMember.stream()
                .map(TrainerInfoByTicketResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
