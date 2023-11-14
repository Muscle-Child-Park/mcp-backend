package com.park.muscle.core.lesson.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exercise {

    @Id
    @Column(name = "EXERCISE_ID")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LESSON_ID", nullable = false)
    private Lesson lesson;

    @Builder
    public Exercise(ExerciseType exerciseType, String name, String kind, String weight, int count, int runTime, Lesson lesson) {
        this.exerciseType = exerciseType;
        this.name = name;
        this.kind = kind;
        this.weight = weight;
        this.count = count;
        this.runTime = runTime;
        this.lesson = lesson;
    }
}
