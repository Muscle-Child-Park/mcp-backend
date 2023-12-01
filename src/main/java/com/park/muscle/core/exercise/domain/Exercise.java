package com.park.muscle.core.exercise.domain;

import com.park.muscle.core.exercise.dto.ExerciseRequestDto.UpdateExerciseWithPersonal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "exercise")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exercise {

    @Id
    @Column(name = "exercise_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exercise_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ExerciseType exerciseType;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "weight",nullable = false)
    private String weight;

    @Column(name = "reps", nullable = false)
    private int reps;

    @Column(name = "sets",nullable = false)
    private String sets;

    @Column(name = "run_time", nullable = false)
    private int runTime;

    @Builder
    public Exercise(ExerciseType exerciseType, String name, String sets, String weight, int reps, int runTime) {
        this.exerciseType = exerciseType;
        this.name = name;
        this.weight = weight;
        this.reps = reps;
        this.sets = sets;
        this.runTime = runTime;
    }

    public void updateExercise(UpdateExerciseWithPersonal exerciseUpdateRequestDto) {
        this.exerciseType = ExerciseType.findType(exerciseUpdateRequestDto.getExerciseType());
        this.name = exerciseUpdateRequestDto.getName();
        this.weight = exerciseUpdateRequestDto.getWeight();
        this.reps = exerciseUpdateRequestDto.getReps();
        this.sets = exerciseUpdateRequestDto.getSets();
        this.runTime = exerciseUpdateRequestDto.getRunTime();
    }
}
