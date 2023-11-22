package com.park.muscle.core.exercise.dto;

import com.park.muscle.core.exercise.domain.ExerciseDiary;
import lombok.Builder;
import lombok.Getter;

public class LogResponseDto {

    @Getter
    @Builder
    public static class LogReflectionResponseDto {
        private long logId;

        public static LogReflectionResponseDto fromEntity(ExerciseDiary exerciseDiary) {
            return LogReflectionResponseDto.builder()
                    .logId(exerciseDiary.getId())
                    .build();
        }
    }

}
