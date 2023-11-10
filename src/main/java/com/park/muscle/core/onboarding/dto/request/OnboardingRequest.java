package com.park.muscle.core.onboarding.dto.request;

import com.park.muscle.core.onboarding.domain.Onboarding;
import lombok.Getter;

@Getter
public class OnboardingRequest {

    private final Long id;

    private final String firstPurpose;

    private final String secondPurpose;

    private final String balance;

    private final String interest;

    private final String lifeStyle;

    private final String eatingHabit;

    private final String name;

    private final String uniqueTag;

    public OnboardingRequest(Onboarding onboarding) {
        this.id = onboarding.getId();
        this.firstPurpose = onboarding.getFirstPurpose();
        this.secondPurpose = onboarding.getSecondPurpose();
        this.balance = onboarding.getBalance();
        this.interest = onboarding.getInterest();
        this.lifeStyle = onboarding.getLifeStyle();
        this.eatingHabit = onboarding.getEatingHabit();
        this.name = onboarding.getName();
        this.uniqueTag = onboarding.getMemberTag();
    }
}
