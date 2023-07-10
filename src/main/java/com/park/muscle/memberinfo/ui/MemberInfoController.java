package com.park.muscle.memberinfo.ui;

import com.park.muscle.memberinfo.command.application.MemberInfoService;
import com.park.muscle.memberinfo.command.domain.Height;
import com.park.muscle.memberinfo.command.domain.InBody;
import com.park.muscle.memberinfo.command.domain.MemberInfo;
import com.park.muscle.memberinfo.command.domain.Weight;
import com.park.muscle.memberinfo.query.MemberInfoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memberInfo")
public class MemberInfoController {

    private final MemberInfoService memberInfoService;

    public MemberInfoController(MemberInfoService memberInfoService) {
        this.memberInfoService = memberInfoService;
    }

    @PostMapping
    public ResponseEntity<String> createMemberInfo(@RequestBody MemberInfoRequest memberInfoRequest) {

        Height height = memberInfoRequest.getHeight();
        Weight weight = memberInfoRequest.getWeight();
        InBody inBody = memberInfoRequest.getInBody();

        MemberInfo memberInfo = new MemberInfo(height, weight, inBody);

        memberInfoService.createMemberInfo(memberInfo);

        return ResponseEntity.ok("MemberInfo create successfully");
    }
}
