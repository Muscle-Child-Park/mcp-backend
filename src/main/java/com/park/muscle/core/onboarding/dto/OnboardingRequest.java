package com.park.muscle.core.onboarding.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Tag(name = "유저 온보딩 수정 요청 DTO")
public class OnboardingRequest {

    @Getter
    @NoArgsConstructor
    public static class UpdateRequest {
        @Schema(description = "신체 목표")
        private String bodyPurpose;
        @Schema(description = "운동 목적")
        private String exercisePurpose;
        @Schema(description = "식단, 운동 밸런스")
        private String balance;
        @Schema(description = "운동 관심도")
        private String interest;
        @Schema(description = "평소 생활 습관")
        private String lifeStyle;
        @Schema(description = "식습관", deprecated = true)
        private String eatingHabit;
        @Schema(description = "회원 이름")
        private String name;
    }
}
