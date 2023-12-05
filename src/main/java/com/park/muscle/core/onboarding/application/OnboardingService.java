package com.park.muscle.core.onboarding.application;

import static com.park.muscle.core.onboarding.dto.OnboardingResponse.UpdateOnboarding;

import com.park.muscle.core.member.application.MemberService;
import com.park.muscle.core.member.domain.Member;
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
    private final MemberService memberService;

    public Onboarding findOwnOnboard(Long memberId) {
        Member member = memberService.findMemberById(memberId);
        return member.getOnboarding();
    }

    @Transactional
    public UpdateOnboarding updateOnboard(final Long memberId, final UpdateRequest request) {
        Member member = memberService.findMemberById(memberId);
        Onboarding existOnboarding = findOwnOnboard(memberId);

        existOnboarding.updateOnboarding(request);
        member.updateOnboarding(existOnboarding);

        onboardingRepository.save(existOnboarding);
        memberService.save(member);
        return UpdateOnboarding.fromEntity(existOnboarding.getId());
    }
}
