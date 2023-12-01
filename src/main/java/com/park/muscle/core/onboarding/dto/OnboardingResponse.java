package com.park.muscle.core.onboarding.dto;

import com.park.muscle.core.onboarding.domain.Onboarding;
import lombok.Builder;
import lombok.Getter;

public class OnboardingResponse {


    @Getter
    @Builder
    public static class FindResponse {
        private String firstPurpose;
        private String secondPurpose;
        private String balance;
        private String interest;
        private String lifeStyle;
        private String eatingHabit;
        private String name;

        public static FindResponse fromEntity(Onboarding onboarding) {
            return FindResponse.builder()
                    .firstPurpose(onboarding.getFirstPurpose())
                    .secondPurpose(onboarding.getSecondPurpose())
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
