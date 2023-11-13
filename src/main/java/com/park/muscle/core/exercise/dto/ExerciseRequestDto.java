package com.park.muscle.core.exercise.dto;

import com.park.muscle.core.exercise.domain.Exercise;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

public class ExerciseRequestDto {

    @Getter
    public static class Create {
        private Long lessonId;

        @NotBlank(message = "Exercise name is required")
        private String name;

        @NotBlank(message = "Exercise type is required")
        private String type;

        @NotBlank(message = "Exercise weight is required")
        private String weight;

        @NotNull(message = "Exercise count is required")
        private int count;

        public Exercise toEntity() {
            return Exercise.builder()
                    .name(name)
                    .type(type)
                    .weight(weight)
                    .count(count)
                    .build();
        }
    }
}
