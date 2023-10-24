package com.park.muscle.core.member.presentation;

import com.park.muscle.core.member.application.MemberService;
import com.park.muscle.core.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberProfileController {

    private final MemberService memberService;

    @GetMapping("/{memberId}/my-page")
    public Member getMemberPage(@PathVariable Long memberId) {
        Member member = memberService.getMemberById(memberId);
        return member;
    }
}
