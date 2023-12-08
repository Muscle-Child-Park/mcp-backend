package com.park.muscle.core.exercise.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExerciseTest {
    private Exercise exercise;

    @BeforeEach
    void setup() {
        ExerciseType exerciseType = ExerciseType.ANAEROBIC;
        String name = "벤치 프레스";
        String weight = "70";
        String sets = "4";
        int runTime = 50;
        int reps = 5;

        exercise = Exercise.builder()
                .name(name)
                .runTime(runTime)
                .exerciseType(exerciseType)
                .weight(weight)
                .reps(reps)
                .sets(sets)
                .build();
    }

    @DisplayName("운동 업데이트 테스트")
    @Test
    void 운동_업데이트() {
        exercise.updateExercise(ExerciseType.ANAEROBIC, "스쿼트", "90", 4, "5", 60);
        assertThat(exercise.getName()).isEqualTo("스쿼트");
        assertThat(exercise.getWeight()).isEqualTo("90");
    }
}