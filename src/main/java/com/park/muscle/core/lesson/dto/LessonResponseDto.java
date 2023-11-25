package com.park.muscle.core.lesson.dto;

import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.exercise.dto.LogResponseDto.LogReflectionResponseDto;
import com.park.muscle.core.lesson.domain.Lesson;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class LessonResponseDto {

    @Getter
    @Builder
    public static class LessonCreateResponse {
        private Long ticketId;

        private Long lessonId;

        private String title;

        private LocalDateTime lessonDate;

        private String timeSlot;

        private String feedback;

        private boolean completionToggle;

        public static LessonCreateResponse fromEntity(Lesson lesson, long ticketId) {
            return LessonCreateResponse.builder()
                    .ticketId(ticketId)
                    .lessonId(lesson.getId())
                    .title(lesson.getTitle())
                    .lessonDate(lesson.getLessonDate())
                    .timeSlot(lesson.getTimeSlot())
                    .feedback(lesson.getFeedback())
                    .completionToggle(lesson.isCompletionToggle())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class LessonRetrieveResponse {
        private String title;
        private LocalDateTime lessonDate;
        private String timeSlot;
        private String feedback;
        private boolean isCompleted;
        private List<Exercise> exercise;
        private LogReflectionResponseDto logReflectionResponseDto;

        public static LessonRetrieveResponse fromEntity(Lesson lesson,
                                                        List<Exercise> exercise,
                                                        LogReflectionResponseDto logReflectionResponseDto) {
            return LessonRetrieveResponse.builder()
                    .title(lesson.getTitle())
                    .lessonDate(lesson.getLessonDate())
                    .timeSlot(lesson.getTimeSlot())
                    .feedback(lesson.getFeedback())
                    .isCompleted(lesson.checkLessonCompletion())
                    .exercise(exercise)
                    .logReflectionResponseDto(logReflectionResponseDto)
                    .build();
        }
    }

}
