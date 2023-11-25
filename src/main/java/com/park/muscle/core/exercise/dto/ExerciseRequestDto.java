package com.park.muscle.core.exercise.dto;

import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.exercise.domain.ExerciseType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

public class ExerciseRequestDto {

    @Getter
    public static class CreateExerciseWithLesson {
        @NotBlank(message = "Exercise type is required")
        private String exerciseType;

        @NotBlank(message = "Exercise name is required")
        private String name;

        @NotBlank(message = "Exercise kind is required")
        private String kind;

        @NotBlank(message = "Exercise weight is required")
        private String weight;

        @NotNull(message = "Exercise count is required")
        private int count;

        private int runTime;

        private Long lessonId;

        public Exercise toEntity() {
            return Exercise.builder()
                    .exerciseType(ExerciseType.findType(exerciseType))
                    .name(name)
                    .kind(kind)
                    .weight(weight)
                    .count(count)
                    .runTime(runTime)
                    .build();
        }
    }

    @Getter
    public static class CreateExerciseWithPersonal {
        @NotBlank(message = "Exercise type is required")
        private String exerciseType;

        @NotBlank(message = "Exercise name is required")
        private String name;

        @NotBlank(message = "Exercise kind is required")
        private String kind;

        @NotBlank(message = "Exercise weight is required")
        private String weight;

        @NotNull(message = "Exercise count is required")
        private int count;

        private int runTime;

        public Exercise toEntity() {
            return Exercise.builder()
                    .exerciseType(ExerciseType.findType(exerciseType))
                    .name(name)
                    .kind(kind)
                    .weight(weight)
                    .count(count)
                    .runTime(runTime)
                    .build();
        }
    }

    @Getter
    public static class UpdateExerciseWithPersonal {
        @NotNull(message = "Exercise Id IS Required")
        private long exerciseId;

        @NotBlank(message = "Exercise type is required")
        private String exerciseType;

        @NotBlank(message = "Exercise name is required")
        private String name;

        @NotBlank(message = "Exercise kind is required")
        private String kind;

        @NotBlank(message = "Exercise weight is required")
        private String weight;

        @NotNull(message = "Exercise count is required")
        private int count;

        private int runTime;
    }
}
