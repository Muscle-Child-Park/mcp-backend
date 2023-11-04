package com.park.muscle.core.onboarding.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateOnboardingRequest {

    private String firstPurpose;

    private String secondPurpose;

    private String balance;

    private String interest;

    private String lifeStyle;

    private String eatingHabit;

    private String name;
}
