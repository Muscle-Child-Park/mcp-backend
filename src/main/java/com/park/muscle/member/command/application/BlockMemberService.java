package com.park.muscle.member.command.application;

import com.park.muscle.member.command.domain.Member;
import com.park.muscle.member.command.domain.MemberId;
import com.park.muscle.member.command.domain.MemberRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * TODO: 2023-07-7, Fri, 18:40  -JEON
 *  TASK: Member가 아닐 경우 예외 발생 -> 스프링 시큐리티로 대체 가능할듯함
 *       상의 후 수정 | 삭제 예정
 */
@Service
public class BlockMemberService {

    private MemberRepository memberRepository;

    public BlockMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PreAuthorize("hasRole('MEMBER')")
    @Transactional
    public void block(String memberId) {
        Member member = memberRepository.findById(new MemberId(Long.parseLong(memberId)))
                .orElseThrow(() -> new NoMemberException());

//        member.block();
    }
}
