package com.park.muscle.core.exercise.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exercise {

    @Id
    @Column(name = "exercise_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ExerciseType exerciseType;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String kind;

    @Column(nullable = false)
    private String weight;

    @Column(nullable = false)
    private int count;

    @Column(nullable = false)
    private int runTime;

    @Builder
    public Exercise(ExerciseType exerciseType, String name, String kind, String weight, int count, int runTime) {
        this.exerciseType = exerciseType;
        this.name = name;
        this.kind = kind;
        this.weight = weight;
        this.count = count;
        this.runTime = runTime;
    }
}
