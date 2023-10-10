package com.park.muscle.core.member.application;

import static com.park.muscle.global.exception.ErrorCode.MEMBER_NOT_FOUND;

import com.park.muscle.core.jwt.application.JwtTokenProvider;
import com.park.muscle.core.jwt.dto.ReIssueTokenDto;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.member.domain.MemberRepository;
import com.park.muscle.core.member.dto.response.LoginResponseDto;
import com.park.muscle.core.member.exception.MemberNotFoundException;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.global.util.CookieUtil;
import com.park.muscle.global.util.HttpHeaderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    public Member getMemberById(Long id) {
        log.info("해당 uuid를 가진 멤버를 찾습니다.");
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));
    }

    public static HttpHeaders setCookieAndHeader(LoginResponseDto loginResult) {
        HttpHeaders headers = new HttpHeaders();
        CookieUtil.setRefreshCookie(headers, loginResult.getRefreshToken());
        HttpHeaderUtil.setAccessToken(headers, loginResult.getAccessToken());
        return headers;
    }

    public static HttpHeaders setCookieAndHeader(ReIssueTokenDto reIssueTokenDto) {
        HttpHeaders headers = new HttpHeaders();
        HttpHeaderUtil.setAccessToken(headers, reIssueTokenDto.getAccessToken());
        CookieUtil.setRefreshCookie(headers, reIssueTokenDto.getRefreshToken());
        return headers;
    }

    public Trainer getTrainerInfo(final Long memberId, final Long trainerId) {
        return null;
    }
}
