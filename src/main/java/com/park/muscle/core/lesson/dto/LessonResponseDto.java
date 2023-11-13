package com.park.muscle.core.lesson.dto;

import com.park.muscle.core.exercise.dto.ExerciseRequestDto;
import com.park.muscle.core.lesson.domain.Lesson;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class LessonResponseDto {

    @Getter
    @Builder
    public static class LessonCreateResponse {
        private final Long lessonId;
        private final Long ticketId;
        private final List<ExerciseRequestDto.Create> exercises;
        private final LocalDateTime lessonDate;
        private final String timeSlot;

        public static LessonCreateResponse fromEntity(LessonRequestDto.Create lessonRequestDto, Lesson lesson) {
            return new LessonCreateResponse(
                    lesson.getId(),
                    lessonRequestDto.getTicketId(),
                    lessonRequestDto.getExercises(),
                    lessonRequestDto.getLessonDate(),
                    lessonRequestDto.getTimeSlot()
            );
        }
    }
}
