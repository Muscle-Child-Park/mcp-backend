package com.park.muscle.core.lesson.dto;

import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.exercise.dto.ExerciseRequestDto;
import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.ticket.domain.Ticket;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LessonRequestDto {

    @Getter
    @NoArgsConstructor
    public static class Create {
        @NotNull(message = "Ticket ID is required")
        private Long ticketId;

        private List<ExerciseRequestDto.Create> exercises;

        @NotNull(message = "Lesson date is required")
        private LocalDateTime lessonDate;

        @NotNull(message = "Lesson time slot is required")
        private String timeSlot;

        public Lesson toEntity(Ticket ticket, List<Exercise> exercises) {
            return Lesson.builder()
                    .ticket(ticket)
                    .exercises(exercises)
                    .lessonDate(lessonDate)
                    .timeSlot(timeSlot)
                    .build();
        }
    }
}
