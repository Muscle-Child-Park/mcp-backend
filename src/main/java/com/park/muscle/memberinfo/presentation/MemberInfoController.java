package com.park.muscle.memberinfo.presentation;

import com.park.muscle.memberinfo.application.MemberInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memberInfo")
public class MemberInfoController {

    private final MemberInfoService memberInfoService;

    public MemberInfoController(MemberInfoService memberInfoService) {
        this.memberInfoService = memberInfoService;
    }
}