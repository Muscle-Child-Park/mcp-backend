package com.park.muscle.core.onboarding.application;

import com.park.muscle.core.member.exception.MemberNotFoundException;
import com.park.muscle.core.onboarding.domain.Onboarding;
import com.park.muscle.core.onboarding.domain.OnboardingRepository;
import com.park.muscle.core.onboarding.dto.request.UpdateOnboardingRequest;
import com.park.muscle.core.onboarding.dto.response.UpdateOnboardingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OnboardingService {

    private final OnboardingRepository onboardingRepository;

    public Onboarding findOwn(Long memberId) {
        return onboardingRepository.findByMemberId(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Transactional
    public UpdateOnboardingResponse update(final Long memberId, final UpdateOnboardingRequest request) {
        Onboarding existOnboarding = onboardingRepository.findByMemberId(memberId)
                .orElseThrow(MemberNotFoundException::new);
        existOnboarding.updateOnboarding(request);
        return new UpdateOnboardingResponse(existOnboarding);
    }
}
