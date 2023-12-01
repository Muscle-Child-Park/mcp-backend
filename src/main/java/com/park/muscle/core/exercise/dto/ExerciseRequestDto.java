package com.park.muscle.core.exercise.dto;

import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.exercise.domain.ExerciseType;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ExerciseRequestDto {

    @Getter
    public static class CreateExerciseWithLesson {
        @NotBlank(message = "Exercise type is required")
        @Schema(description = "Enter the exercise type", example = "AEROBIC or ANAEROBIC")
        private String exerciseType;

        @NotBlank(message = "Exercise name is required")
        @Schema(description = "Enter the exercise name", example = "Deadlift ..")
        private String name;

        @NotBlank(message = "Exercise weight is required")
        @Schema(description = "Enter the exercise weight", example = "50")
        private String weight;

        @NotNull(message = "Exercise reps is required")
        @Schema(description = "Enter the exercise reps", example = "12")
        private int reps;

        @NotBlank(message = "Exercise sets is required")
        @Schema(description = "Enter the exercise sets", example = "4")
        private String sets;

        @Schema(description = "Enter is required for aerobic exercise", example = "50")
        private int runTime;

        public Exercise toEntity() {
            return Exercise.builder()
                    .exerciseType(ExerciseType.findType(exerciseType))
                    .name(name)
                    .weight(weight)
                    .reps(reps)
                    .sets(sets)
                    .runTime(runTime)
                    .build();
        }
    }

    @Getter
    public static class CreateExerciseWithPersonal {
        @NotBlank(message = "Exercise type is required")
        @Schema(description = "Enter the exercise type", example = "AEROBIC or ANAEROBIC")
        private String exerciseType;

        @NotBlank(message = "Exercise name is required")
        @Schema(description = "Enter the exercise name", example = "Deadlift ..")
        private String name;

        @NotBlank(message = "Exercise set is required")
        @Schema(description = "Enter the exercise set", example = "5")
        private String sets;

        @NotBlank(message = "Exercise weight is required")
        @Schema(description = "Enter the exercise weight", example = "50")
        private String weight;

        @NotNull(message = "Exercise count is required")
        @Schema(description = "Enter the exercise reps", example = "12")
        private int reps;

        @Schema(description = "Enter is required for aerobic exercise", example = "60")
        private int runTime;

        public Exercise toEntity() {
            return Exercise.builder()
                    .exerciseType(ExerciseType.findType(exerciseType))
                    .name(name)
                    .sets(sets)
                    .weight(weight)
                    .reps(reps)
                    .runTime(runTime)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class UpdateExerciseWithPersonal {
        @NotNull(message = "Exercise Id IS Required")
        private long exerciseId;

        @NotBlank(message = "Exercise type is required")
        @Schema(description = "Enter the exercise type", example = "AEROBIC or ANAEROBIC")
        private String exerciseType;

        @NotBlank(message = "Exercise name is required")
        @Schema(description = "Enter the exercise name", example = "Deadlift ..")
        private String name;

        @NotBlank(message = "Exercise sets is required")
        @Schema(description = "Enter the exercise set", example = "5")
        private String sets;

        @NotBlank(message = "Exercise weight is required")
        @Schema(description = "Enter the exercise weight", example = "50")
        private String weight;

        @NotNull(message = "Exercise reps is required")
        @Schema(description = "Enter the exercise reps", example = "12")
        private int reps;

        @Schema(description = "Enter is required for aerobic exercise", example = "60")
        private int runTime;
    }
}
