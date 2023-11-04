package com.park.muscle.core.onboarding.dto.response;

import com.park.muscle.core.onboarding.domain.Onboarding;
import lombok.Getter;

@Getter
public class UpdateOnboardingResponse {

    private String firstPurpose;

    private String secondPurpose;

    private String balance;

    private String interest;

    private String lifeStyle;

    private String eatingHabit;

    private String name;

    public UpdateOnboardingResponse(Onboarding onboarding) {
        this.firstPurpose = onboarding.getFirstPurpose();
        this.secondPurpose = onboarding.getSecondPurpose();
        this.balance = onboarding.getBalance();
        this.interest = onboarding.getInterest();
        this.lifeStyle = onboarding.getLifeStyle();
        this.eatingHabit = onboarding.getEatingHabit();
        this.name = onboarding.getEatingHabit();
    }
}
