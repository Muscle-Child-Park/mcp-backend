package com.park.muscle.core.personalexercise.dto.response;

import com.park.muscle.core.exercise.domain.ClassType;
import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.exercise.dto.LogResponseDto.LogReflectionResponseDto;
import com.park.muscle.core.personalexercise.domain.PersonalExercise;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class PersonalExerciseResponse {

    @Getter
    @Builder
    public static class PersonalExerciseCreateResponse {
        private long id;

        public static PersonalExerciseCreateResponse fromEntity(PersonalExercise personalExercise) {
            return PersonalExerciseCreateResponse.builder()
                    .id(personalExercise.getId())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class AllPersonalExerciseResponse {
        private Long personalExerciseId;

        private String title;

        private LocalDateTime lessonDate;

        private String timeSlot;

        private boolean completionToggle;

        private ClassType classType;

        public static AllPersonalExerciseResponse fromEntity(PersonalExercise personalExercise) {
            return AllPersonalExerciseResponse.builder()
                    .personalExerciseId(personalExercise.getId())
                    .title(personalExercise.getTitle())
                    .lessonDate(personalExercise.getLessonDate())
                    .timeSlot(personalExercise.getTimeSlot())
                    .completionToggle(personalExercise.isCompletionToggle())
                    .classType(personalExercise.getClassType())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class OwnPersonalExerciseResponse {
        private List<Exercise> exercise;

        private String title;

        private LocalDateTime lessonDate;

        private String timeSlot;

        private boolean completionToggle;

        private LogReflectionResponseDto logReflectionResponseDto;

        public static OwnPersonalExerciseResponse fromEntity(PersonalExercise personalExercise, List<Exercise> exercise,
                                                             LogReflectionResponseDto logReflectionResponseDto) {
            return OwnPersonalExerciseResponse.builder()
                    .title(personalExercise.getTitle())
                    .lessonDate(personalExercise.getLessonDate())
                    .timeSlot(personalExercise.getTimeSlot())
                    .completionToggle(personalExercise.isCompletionToggle())
                    .exercise(exercise)
                    .logReflectionResponseDto(logReflectionResponseDto)
                    .build();
        }
    }
}
