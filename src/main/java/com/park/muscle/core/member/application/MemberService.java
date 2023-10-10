package com.park.muscle.core.member.application;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.member.domain.MemberRepository;
import com.park.muscle.core.member.domain.SocialType;
import com.park.muscle.core.member.dto.request.FurtherInfoRequest;
import com.park.muscle.core.member.dto.request.OnboardingQuestionRequest;
import com.park.muscle.core.member.dto.request.SignUpRequest;
import com.park.muscle.core.member.exception.MemberNotFoundException;
import com.park.muscle.core.onboarding.domain.Onboarding;
import com.park.muscle.core.onboarding.domain.OnboardingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final OnboardingRepository onboardingRepository;

    public MemberService(final MemberRepository memberRepository, final OnboardingRepository onboardingRepository) {
        this.memberRepository = memberRepository;
        this.onboardingRepository = onboardingRepository;
    }

    @Transactional
    public Long signUp(final SignUpRequest signUpRequest) {
        Member member = Member.builder()
                .socialId(signUpRequest.getSocialId())
                .socialType(SocialType.findType(signUpRequest.getSocialType()))
                .build();

        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    @Transactional
    public void addFurtherInfo(final Long memberId, final FurtherInfoRequest furtherInfoRequest) {
        Member member = findMemberObject(memberId);
        member.updateName(furtherInfoRequest.getName());
    }

    @Transactional
    public void addOnboardingQuestion(final Long memberId, final OnboardingQuestionRequest onboardingQuestionRequest) {
        Member member = findMemberObject(memberId);
        Onboarding onboarding = Onboarding.builder()
                .firstPurpose(onboardingQuestionRequest.getFirstPurpose())
                .secondPurpose(onboardingQuestionRequest.getSecondPurpose())
                .balance(onboardingQuestionRequest.getBalance())
                .interest(onboardingQuestionRequest.getInterest())
                .lifeStyle(onboardingQuestionRequest.getLifeStyle())
                .eatingHabit(onboardingQuestionRequest.getEatingHabit())
                .member(member)
                .build();

        onboardingRepository.save(onboarding);
    }

    private Member findMemberObject(final Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }

}
