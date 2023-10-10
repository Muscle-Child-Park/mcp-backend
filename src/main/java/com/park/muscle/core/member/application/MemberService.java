package com.park.muscle.core.member.application;

import com.park.muscle.core.jwt.application.JwtTokenProvider;
import com.park.muscle.core.jwt.dto.ReIssueTokenDto;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.member.domain.MemberRepository;
import com.park.muscle.core.member.domain.SocialType;
import com.park.muscle.core.member.dto.request.FurtherInfoRequest;
import com.park.muscle.core.member.dto.request.OnboardingQuestionRequest;
import com.park.muscle.core.member.dto.request.SignUpRequest;
import com.park.muscle.core.member.dto.response.SignUpResponse;
import com.park.muscle.core.member.exception.MemberNotFoundException;
import com.park.muscle.core.onboarding.domain.Onboarding;
import com.park.muscle.core.onboarding.domain.OnboardingRepository;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.global.util.CookieUtil;
import com.park.muscle.global.util.HttpHeaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class MemberService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final OnboardingRepository onboardingRepository;

    public MemberService(JwtTokenProvider jwtTokenProvider, MemberRepository memberRepository,
                         OnboardingRepository onboardingRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.memberRepository = memberRepository;
        this.onboardingRepository = onboardingRepository;
    }

    public Member getMemberById(Long id) {
        log.info("해당 uuid를 가진 멤버를 찾습니다.");
        return memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
    }

    public static HttpHeaders setCookieAndHeader(ReIssueTokenDto loginResult) {
        HttpHeaders headers = new HttpHeaders();
        CookieUtil.setRefreshCookie(headers, loginResult.getRefreshToken());
        HttpHeaderUtil.setAccessToken(headers, loginResult.getAccessToken());
        return headers;
    }

    @Transactional
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        Member member = Member.builder()
                .socialId(signUpRequest.getSocialId())
                .socialType(SocialType.findType(signUpRequest.getSocialType()))
                .build();

        Member savedMember = memberRepository.save(member);
        return new SignUpResponse(savedMember.getId());
    }

    @Transactional
    public void addFurtherInfo(Long memberId, FurtherInfoRequest furtherInfoRequest) {
        Member member = findMemberObject(memberId);
        member.updateName(furtherInfoRequest.getName());
    }

    @Transactional
    public void addOnboardingQuestion(Long memberId,
                                      OnboardingQuestionRequest onboardingQuestionRequest) {
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

    private Member findMemberObject(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }

    public Trainer getTrainerInfo(final Long memberId, final Long trainerId) {
        return null;
    }
}

