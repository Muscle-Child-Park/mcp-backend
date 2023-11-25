package com.park.muscle.core.lesson.domain;

import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.exercise.domain.ExerciseDiary;
import com.park.muscle.core.reservation.domain.Reservation;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.global.entity.BaseEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "lesson")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lesson extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime lessonDate;

    @Column(nullable = false)
    private String timeSlot;

    @Column
    private String feedback;

    @Column
    private boolean completionToggle;

    @OneToOne
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Exercise> exercises;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "exercise_diary_id")
    private ExerciseDiary exerciseDiary;

    @Builder
    public Lesson(String title, LocalDateTime lessonDate, String timeSlot, String feedback, List<Exercise> exercises) {
        this.title = title;
        this.lessonDate = lessonDate;
        this.timeSlot = timeSlot;
        this.feedback = feedback;
        this.exercises = exercises;
        this.completionToggle = false;
    }

    public void addExerciseDiary(ExerciseDiary exerciseDiary) {
        this.exerciseDiary = exerciseDiary;
    }

    public void addFeedback(String feedback) {
        if (lessonDate.isBefore(LocalDateTime.now())) {
            this.feedback = feedback;
        } else {
            throw new IllegalStateException("수업이 종료되지 않았습니다.");
        }
    }

    public void updateExercise(final List<Exercise> exercises) {
        if (this.exercises == null) {
            this.exercises = new ArrayList<>();
        }
        this.exercises.addAll(exercises);
    }

    public boolean checkLessonCompletion() {
        LocalDateTime current = lessonDate.plusMinutes(Integer.parseInt(this.timeSlot));
        if (lessonDate.isBefore(current)) {
            return completionToggle;
        }
        return completionToggle = true;
    }

    public void updateExerciseDiary(final ExerciseDiary exerciseDiary) {
        this.exerciseDiary = exerciseDiary;
    }
}
