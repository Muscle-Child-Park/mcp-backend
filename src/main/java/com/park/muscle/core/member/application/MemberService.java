package com.park.muscle.core.member.application;

import com.park.muscle.core.member.domain.MemberRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public static HttpHeaders setCookieAndHeader(ReIssueTokenDto reIssueTokenDto) {
        HttpHeaders headers = new HttpHeaders();
        HttpHeaderUtil.setAccessToken(headers, reIssueTokenDto.getAccessToken());
        CookieUtil.setRefreshCookie(headers, reIssueTokenDto.getRefreshToken());
        return headers;
    }
}
