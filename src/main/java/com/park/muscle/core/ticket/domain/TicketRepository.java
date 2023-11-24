package com.park.muscle.core.ticket.domain;

import com.park.muscle.core.lesson.domain.Lesson;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t.lesson FROM Ticket t WHERE t.id = :ticketId")
    Lesson findLessonByTicketId(@Param("ticketId") Long ticketId);

    @Query("SELECT l FROM Ticket t JOIN Lesson l ON t.lesson = l WHERE t.id = :ticketId")
    List<Lesson> findAllLessonsByTicketId(@Param("ticketId") Long ticketId);

    List<Ticket> findAllTicketByMemberId(Long memberId);

    List<Ticket> findAllTicketByTrainerId(Long trainerId);
}
