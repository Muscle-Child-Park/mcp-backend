package com.park.muscle.core.exercise.dto;

import lombok.Builder;
import lombok.Getter;

public class ExerciseResponseDto {

    @Getter
    @Builder
    public static class ExerciseCreateResponse {
        private final Long exerciseId;
        private final String name;
        private final String type;
        private final String weight;
        private final int count;

        public static ExerciseCreateResponse fromEntity(ExerciseRequestDto.Create exerciseRequestDto, Long exerciseId) {
            return new ExerciseCreateResponse(
                    exerciseId,
                    exerciseRequestDto.getName(),
                    exerciseRequestDto.getType(),
                    exerciseRequestDto.getWeight(),
                    exerciseRequestDto.getCount()
            );
        }
    }
}
