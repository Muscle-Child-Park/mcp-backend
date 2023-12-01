package com.park.muscle.core.onboarding.dto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Tag(name = "유저 온보딩 수정 요청 DTO")
public class OnboardingRequest {

    @Getter
    @NoArgsConstructor
    public static class UpdateRequest {
        private String firstPurpose;
        private String secondPurpose;
        private String balance;
        private String interest;
        private String lifeStyle;
        private String eatingHabit;
        private String name;
    }
}
