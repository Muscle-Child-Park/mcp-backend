package com.park.muscle.core.lesson.dto;

import com.park.muscle.core.lesson.dto.ExerciseResponseDto.CreateResponse;
import lombok.Builder;
import lombok.Getter;

public class LessonResponseDto {

    @Getter
    @Builder
    public static class LessonCreateResponse {
        private LessonRequestDto.Create lesson;
        private CreateResponse exercises;

        public static LessonCreateResponse fromEntity(LessonRequestDto.Create lesson, CreateResponse exercises) {
            return LessonCreateResponse.builder()
                    .lesson(lesson)
                    .exercises(exercises)
                    .build();
        }
    }
}
