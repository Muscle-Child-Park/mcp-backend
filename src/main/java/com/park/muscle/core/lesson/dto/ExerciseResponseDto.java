package com.park.muscle.core.lesson.dto;

import com.park.muscle.core.lesson.domain.Exercise;
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
        private final int count;

        public static CreateResponse fromEntity(Exercise exercise) {
            return CreateResponse.builder()
                    .count(exercise.getCount())
                    .name(exercise.getName())
                    .exerciseId(exercise.getId())
                    .type(exercise.getKind())
                    .weight(exercise.getWeight())
                    .build();
        }
    }
}
