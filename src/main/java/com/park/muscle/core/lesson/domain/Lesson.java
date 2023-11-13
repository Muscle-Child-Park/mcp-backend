package com.park.muscle.core.lesson.domain;

import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.exercise.domain.ExerciseDiary;
import com.park.muscle.global.entity.BaseEntity;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
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
    @Column(name = "lesson_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "lesson_id", nullable = false)
    private List<Exercise> exercises;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXERCISE_DIARY_ID")
    private ExerciseDiary exerciseDiary;

    @Column(nullable = false)
    private LocalDateTime lessonDate;

    @Column(nullable = false)
    private String timeSlot;

    @Column
    private String feedback;

    @Builder
    public Lesson(Ticket ticket, List<Exercise> exercises, LocalDateTime lessonDate, String timeSlot) {
        this.ticket = ticket;
        this.exercises = exercises;
        this.lessonDate = lessonDate;
        this.timeSlot = timeSlot;
    }
}
