package com.park.muscle.core.lesson.dto;

import com.park.muscle.core.exercise.domain.ClassType;
import com.park.muscle.core.lesson.domain.Lesson;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LessonRequest {

    @Getter
    @NoArgsConstructor
    public static class LessonCreate {

        @NotNull(message = "Ticket ID is required")
        @Schema(description = "need a ticket ID to create a class")
        private Long ticketId;

        @NotNull(message = "Lesson title is required")
        @Schema(description = "Purpose of CO-PT")
        private String title;

        @NotNull(message = "Lesson date is required")
        @Schema(description = "class date of CO-PT")
        private LocalDateTime lessonDate;

        @NotNull(message = "Lesson time slot is required")
        @Schema(description = "class time")
        private String timeSlot;

        @NotNull(message = "Lesson type is required")
        @Schema(description = "class type", example = "CO_PT")
        private String classType;

        @Schema(description = "Feedback can ba added even after the class is over")
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
