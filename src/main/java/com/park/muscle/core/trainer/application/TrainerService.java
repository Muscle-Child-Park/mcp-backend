package com.park.muscle.core.trainer.application;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.member.domain.MemberRepository;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.core.trainer.domain.TrainerRepository;
import com.park.muscle.core.trainer.dto.request.ClassRegistrationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final MemberRepository memberRepository;

    public String completeSignUp(Trainer trainer) {
        return null;
    }

    public boolean approveMemberRegistration(Long trainerId, Long memberId,
                                             ClassRegistrationRequest classRegistrationRequest) {
        return false;
    }

    public Member getMemberInfo(Long trainerId, Long memberId) {
        Member memberInfo = trainerRepository.getMemberInfo(trainerId, memberId);
        return memberInfo;
    }
}
