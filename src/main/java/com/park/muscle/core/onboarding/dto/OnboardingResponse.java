package com.park.muscle.core.onboarding.dto;

import com.park.muscle.core.onboarding.domain.Onboarding;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class OnboardingResponse {


    @Getter
    @Builder
    public static class FindResponse {
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

        public static FindResponse fromEntity(Onboarding onboarding) {
            return FindResponse.builder()
                    .bodyPurpose(onboarding.getBodyPurpose())
                    .exercisePurpose(onboarding.getExercisePurpose())
                    .balance(onboarding.getBalance())
                    .interest(onboarding.getInterest())
                    .lifeStyle(onboarding.getLifeStyle())
                    .eatingHabit(onboarding.getEatingHabit())
                    .name(onboarding.getName())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class UpdateOnboarding {
        private long onboardingId;

        public static UpdateOnboarding fromEntity(final Long onboardingId) {
            return UpdateOnboarding.builder()
                    .onboardingId(onboardingId)
                    .build();
        }
    }
}
