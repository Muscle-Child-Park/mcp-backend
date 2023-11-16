package com.park.muscle.core.lesson.dto;

import com.park.muscle.core.lesson.domain.Lesson;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LessonRequestDto {

    @Getter
    @NoArgsConstructor
    public static class Create {
        @NotNull(message = "Lesson date is required")
        private LocalDateTime lessonDate;

        @NotNull(message = "Lesson time slot is required")
        private String timeSlot;

        private String feedback;

        public Lesson toEntity() {
            return Lesson.builder()
                    .lessonDate(lessonDate)
                    .timeSlot(timeSlot)
                    .feedback(feedback)
                    .build();
        }
    }
}
