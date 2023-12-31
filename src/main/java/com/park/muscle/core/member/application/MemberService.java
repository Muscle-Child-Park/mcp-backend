package com.park.muscle.core.member.application;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.member.domain.MemberRepository;
import com.park.muscle.core.member.exception.MemberNotFoundException;
import com.park.muscle.core.ticket.application.TicketService;
import com.park.muscle.core.trainer.domain.Trainer;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TicketService ticketService;

    public Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
    }

    public Optional<Member> getMemberBySocialId(String username) {
        return memberRepository.findByUsername(username);
    }

    public Member saveSocialInfo(Member member) {
        return memberRepository.save(member);
    }

    public List<Trainer> getTrainerInfo(final Long memberId) {
        return ticketService.findAllTrainerByMemberId(memberId);
    }

    public void save(final Member memberById) {
        memberRepository.save(memberById);
    }
}
