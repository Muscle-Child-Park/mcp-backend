package com.park.muscle.core.personalexercise.domain;

import com.park.muscle.core.exercise.domain.ClassType;
import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.exercise.domain.ExerciseDiary;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "personal_exercise")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personal_exercise_id")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime lessonDate;

    @Column(nullable = false)
    private String timeSlot;

    @Column
    private boolean completionToggle;

    @Column
    private ClassType classType;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Exercise> exercises = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "exercise_diary_id")
    private ExerciseDiary exerciseDiary;

    @Builder
    public PersonalExercise(LocalDateTime lessonDate, String timeSlot, List<Exercise> exercises, ClassType classType) {
        this.lessonDate = lessonDate;
        this.timeSlot = timeSlot;
        this.exercises = exercises;
        this.completionToggle = false;
        this.classType = classType;
    }

    public void addExerciseDiary(ExerciseDiary exerciseDiary) {
        this.exerciseDiary = exerciseDiary;
    }

    public void updateExercise(final List<Exercise> exercises) {
        if (this.exercises == null) {
            this.exercises = new ArrayList<>();
        }
        this.exercises.addAll(exercises);
    }

    public void updateExerciseDiary(final ExerciseDiary exerciseDiary) {
        this.exerciseDiary = exerciseDiary;
    }
}
