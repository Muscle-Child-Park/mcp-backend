package com.park.muscle.member.command.application;

import com.park.muscle.member.command.domain.Member;
import com.park.muscle.member.command.domain.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void createMember(Member member) {
        memberRepository.save(member);
    }
}
