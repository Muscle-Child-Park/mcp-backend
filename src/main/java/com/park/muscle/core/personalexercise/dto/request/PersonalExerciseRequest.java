package com.park.muscle.core.personalexercise.dto.request;

import com.park.muscle.core.exercise.domain.ClassType;
import com.park.muscle.core.personalexercise.domain.PersonalExercise;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PersonalExerciseRequest {

    @Getter
    @NoArgsConstructor
    public static class Create {
        @NotNull(message = "Personal-Exercise title is required")
        private String title;

        @NotNull(message = "Personal-Exercise date is required")
        private LocalDateTime lessonDate;

        @NotNull(message = "Personal-Exercise time slot is required")
        private String timeSlot;

        @NotBlank(message = "Class type is required")
        private String classType;

        public PersonalExercise toEntity() {
            return PersonalExercise.builder()
                    .title(title)
                    .lessonDate(lessonDate)
                    .timeSlot(timeSlot)
                    .classType(ClassType.findType(classType))
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Update {
        @NotNull(message = "Personal-Exercise title is required")
        private String title;

        @NotNull(message = "Personal-Exercise date is required")
        private LocalDateTime lessonDate;

        @NotNull(message = "Personal-Exercise time slot is required")
        private String timeSlot;

        @NotBlank(message = "Class type is required")
        private String classType;

        private boolean completionToggle;
    }
}
