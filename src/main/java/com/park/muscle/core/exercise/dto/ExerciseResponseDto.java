package com.park.muscle.core.exercise.dto;

import com.park.muscle.core.exercise.domain.Exercise;
import lombok.Builder;
import lombok.Getter;

public class ExerciseResponseDto {

    @Getter
    @Builder
    public static class CreateResponse {
        private final Long exerciseId;
        private final String name;
        private final String type;
        private final String weight;
        private final String set;
        private final int reps;

        public static CreateResponse fromEntity(Exercise exercise) {
            return CreateResponse.builder()
                    .exerciseId(exercise.getId())
                    .type(exercise.getExerciseType().name())
                    .name(exercise.getName())
                    .weight(exercise.getWeight())
                    .reps(exercise.getReps())
                    .set(exercise.getSets())
                    .build();
        }
    }
}
