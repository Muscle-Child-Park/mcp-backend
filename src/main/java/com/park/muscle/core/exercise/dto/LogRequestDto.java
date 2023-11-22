package com.park.muscle.core.exercise.dto;

import com.park.muscle.core.exercise.domain.ExerciseDiary;
import com.park.muscle.core.member.domain.Member;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LogRequestDto {

    @Getter
    @NoArgsConstructor
    public static class LogReflectionDto {
        @NotNull(message = "Lesson Id required")
        private long lessonId;
        @NotNull(message = "Member Id required")
        private long memberId;
        @NotBlank(message = "Log Data required")
        private String log;

        public ExerciseDiary toEntity(Member member, String memo) {
            return ExerciseDiary.builder()
                    .member(member)
                    .memo(memo)
                    .build();
        }
    }
}
