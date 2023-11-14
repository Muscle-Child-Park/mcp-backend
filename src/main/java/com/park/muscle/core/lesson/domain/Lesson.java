package com.park.muscle.core.lesson.domain;

import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.global.entity.BaseEntity;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lesson extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LESSON_ID")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime lessonDate;

    @Column(nullable = false)
    private String timeSlot;

    @Column
    private String feedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_ID", nullable = false)
    private Ticket ticket;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lesson")
    private List<Exercise> exercises;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXERCISE_DIARY_ID")
    private ExerciseDiary exerciseDiary;

    @Builder
    public Lesson(LocalDateTime lessonDate, String timeSlot, String feedback, Ticket ticket) {
        this.lessonDate = lessonDate;
        this.timeSlot = timeSlot;
        this.feedback = feedback;
        this.ticket = ticket;
    }
}
