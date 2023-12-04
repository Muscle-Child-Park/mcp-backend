package com.park.muscle.core.onboarding.application;

import static com.park.muscle.core.onboarding.dto.OnboardingResponse.UpdateOnboarding;

import com.park.muscle.core.member.exception.MemberNotFoundException;
import com.park.muscle.core.onboarding.domain.Onboarding;
import com.park.muscle.core.onboarding.domain.OnboardingRepository;
import com.park.muscle.core.onboarding.dto.OnboardingRequest.UpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OnboardingService {

    private final OnboardingRepository onboardingRepository;

    public Onboarding findOwnOnboard(Long memberId) {
        return onboardingRepository.findByMemberId(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Transactional
    public UpdateOnboarding updateOnboard(final Long memberId, final UpdateRequest request) {
        Onboarding existOnboarding = findOwnOnboard(memberId);
        existOnboarding.updateOnboarding(request);
        onboardingRepository.save(existOnboarding);
        return UpdateOnboarding.fromEntity(existOnboarding.getId());
    }
}
