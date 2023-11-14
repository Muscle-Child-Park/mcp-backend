package com.park.muscle.core.lesson.dto;

import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.ticket.domain.Ticket;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LessonRequestDto {

    @Getter
    @NoArgsConstructor
    public static class Create {
        @NotNull(message = "Lesson ticket-ID is required")
        private long ticketId;

        @NotNull(message = "Lesson date is required")
        private LocalDateTime lessonDate;

        @NotNull(message = "Lesson time slot is required")
        private String timeSlot;

        private String feedback;

        public Lesson toEntity(Ticket ticket) {
            return Lesson.builder()
                    .lessonDate(lessonDate)
                    .timeSlot(timeSlot)
                    .feedback(feedback)
                    .ticket(ticket)
                    .build();
        }
    }
}
