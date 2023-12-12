package com.park.muscle.core.member.application;

import com.park.muscle.core.jwt.application.JwtTokenProvider;
import com.park.muscle.core.jwt.dto.ReIssueTokenDto;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.member.domain.MemberRepository;
import com.park.muscle.core.member.dto.request.MemberRequest.LoginRequest;
import com.park.muscle.core.member.dto.request.MemberRequest.OnboardingQuestionRequest;
import com.park.muscle.core.member.dto.response.MemberResponse.LoginResponse;
import com.park.muscle.core.member.dto.response.MemberResponse.SignUpResponse;
import com.park.muscle.core.member.dto.response.MemberResponse.TokenResponse;
import com.park.muscle.core.onboarding.domain.OnboardingRepository;
import com.park.muscle.core.uniquetag.domain.UniqueTagRepository;
import com.park.muscle.global.enumerate.SocialType;
import com.park.muscle.global.util.CookieUtil;
import com.park.muscle.global.util.HttpHeaderUtil;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberAuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final OnboardingRepository onboardingRepository;
    private final UniqueTagRepository uniqueTagRepository;

    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        String userNumber = String.format("%s#%s", SocialType.KAKAO, loginRequest.getSocialId());
        Optional<Member> loginMember = memberService.getMemberBySocialId(userNumber);

        Member member = loginMember.orElseGet(() -> singUp(loginRequest));
        TokenResponse tokenResponse = createTokenResponse(member);
        HttpHeaders headers = setCookieAndHeader(tokenResponse);
        SignUpResponse signUpResponse = SignUpResponse.fromEntity(member);
        return LoginResponse.fromEntity(headers, signUpResponse);
    }

    private TokenResponse createTokenResponse(Member member) {
        if (!member.isNew()) {
            return createSingUpResult(member);
        }
        return createLoginResult(member);
    }

    private TokenResponse createLoginResult(final Member member) {
        String accessToken = jwtTokenProvider.createAccessToken(member.getUniqueTagId(), member.getRole());
        String refreshToken = jwtTokenProvider.getValidRefreshToken(member.getUniqueTagId());
        return TokenResponse.fromEntity(accessToken, refreshToken);
    }

    private Member singUp(final LoginRequest loginRequest) {
        Member member = loginRequest.toEntity();
        uniqueTagRepository.save(member.getUniqueTag());
        return memberService.saveSocialInfo(member);
    }

    private TokenResponse createSingUpResult(final Member member) {
        String accessToken = jwtTokenProvider.createAccessToken(member.getUniqueTagId(), member.getRole());
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getUniqueTagId());

        jwtTokenProvider.saveMemberTokenInRedis(member, refreshToken);
        return TokenResponse.fromEntity(accessToken, refreshToken);
    }

    private void updateMemberName(final LoginRequest loginRequest, final Member member) {
        member.updateName(loginRequest.getName());
    }

    @Transactional
    public void addOnboardingQuestion(Long memberId,
                                      OnboardingQuestionRequest onboardingQuestionRequest) {
        Member member = memberService.findMemberById(memberId);
        member.updateOnboarding(onboardingQuestionRequest.toEntity());
        onboardingRepository.save(member.getOnboarding());
        memberService.save(member);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberService.findMemberById(memberId);
        memberRepository.delete(member);
    }

    public static HttpHeaders setCookieAndHeader(ReIssueTokenDto loginResult) {
        HttpHeaders headers = new HttpHeaders();
        CookieUtil.setRefreshCookie(headers, loginResult.getRefreshToken());
        HttpHeaderUtil.setAccessToken(headers, loginResult.getAccessToken());
        return headers;
    }

    public static HttpHeaders setCookieAndHeader(TokenResponse loginResult) {
        HttpHeaders headers = new HttpHeaders();
        CookieUtil.setRefreshCookie(headers, loginResult.getRefreshToken());
        HttpHeaderUtil.setAccessToken(headers, loginResult.getAccessToken());
        return headers;
    }
}
