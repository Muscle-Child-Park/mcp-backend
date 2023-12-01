package com.park.muscle.core.exercise.dto;

import com.park.muscle.core.exercise.domain.ExerciseDiary;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LogRequestDto {

    @Getter
    @NoArgsConstructor
    public static class LessonLogReflectionDto {
        @NotNull(message = "Lesson Id required")
        private long lessonId;
        @NotNull(message = "Member Id required")
        private long memberId;
        @NotBlank(message = "Log Data required")
        private String log;

        public ExerciseDiary toEntity(String memo) {
            return ExerciseDiary.builder()
                    .memo(memo)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class PersonalLogReflectionDto {
        @NotNull(message = "personal-ex Id required")
        private long personalId;
        @NotNull(message = "Member Id required")
        private long memberId;
        @NotBlank(message = "Log Data required")
        private String log;

        public ExerciseDiary toEntity(String memo) {
            return ExerciseDiary.builder()
                    .memo(memo)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class LessonLogUpdateDto {
        @NotNull(message = "Lesson Id required")
        private long lessonId;
        @NotNull(message = "Exercise-diary Id required")
        private long exerciseDiaryId;
        @NotBlank(message = "Log Data required")
        private String log;
    }

    @Getter
    @NoArgsConstructor
    public static class PersonalLogUpdateDto {
        @NotNull(message = "Lesson Id required")
        private long personalId;
        @NotNull(message = "Exercise-diary Id required")
        private long exerciseDiaryId;
        @NotBlank(message = "Log Data required")
        private String log;
    }
}
