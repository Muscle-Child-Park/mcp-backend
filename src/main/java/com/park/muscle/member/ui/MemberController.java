package com.park.muscle.member.ui;

import com.park.muscle.member.command.application.MemberService;
import com.park.muscle.member.command.domain.Age;
import com.park.muscle.member.command.domain.Email;
import com.park.muscle.member.command.domain.Member;
import com.park.muscle.member.command.domain.Nickname;
import com.park.muscle.member.command.domain.ProviderId;
import com.park.muscle.member.command.domain.ProviderSet;
import com.park.muscle.member.command.domain.Roles;
import com.park.muscle.member.query.dto.MemberRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<String> createMember(@RequestBody MemberRequest memberRequest) {

        Nickname nickname = new Nickname(memberRequest.getNickname());
        Age age = new Age(memberRequest.getAge());
        Email email = new Email(memberRequest.getEmail());
        ProviderId providerId = new ProviderId(memberRequest.getProviderId());
        ProviderSet provider = new ProviderSet(memberRequest.getProvider());
        Roles roles = memberRequest.getRoles();

        Member member = new Member(nickname, age, email, providerId, provider, roles);

        memberService.createMember(member);

        return ResponseEntity.ok("Member create successfully");
    }
}
