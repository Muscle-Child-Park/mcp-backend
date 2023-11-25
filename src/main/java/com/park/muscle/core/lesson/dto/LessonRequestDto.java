package com.park.muscle.core.lesson.dto;

import com.park.muscle.core.exercise.domain.ClassType;
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

        @NotNull(message = "Lesson title is required")
        private String title;

        @NotNull(message = "Lesson date is required")
        private LocalDateTime lessonDate;

        @NotNull(message = "Lesson time slot is required")
        private String timeSlot;

        @NotNull(message = "Lesson type is required")
        private String classType;

        private String feedback;

        public Lesson toEntity() {
            return Lesson.builder()
                    .title(title)
                    .lessonDate(lessonDate)
                    .timeSlot(timeSlot)
                    .classType(ClassType.findType(classType))
                    .feedback(feedback)
                    .build();
        }
    }
}
