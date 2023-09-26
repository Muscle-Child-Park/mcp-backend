package com.park.muscle.core.member.presentation;

import com.park.muscle.core.member.application.MemberService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }
}
