package com.park.muscle.core.member.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OnboardingQuestionRequest {

    @NotBlank
    private String firstPurpose;

    @NotBlank
    private String secondPurpose;

    @NotBlank
    private String balance;

    @NotBlank
    private String interest;

    @NotBlank
    private String lifeStyle;

    @NotBlank
    private String eatingHabit;

    private OnboardingQuestionRequest() {
    }

    @Builder
    private OnboardingQuestionRequest(final String firstPurpose, final String secondPurpose, final String balance,
                                      final String interest,
                                      final String lifeStyle, final String eatingHabit) {
        this.firstPurpose = firstPurpose;
        this.secondPurpose = secondPurpose;
        this.balance = balance;
        this.interest = interest;
        this.lifeStyle = lifeStyle;
        this.eatingHabit = eatingHabit;
    }
}
