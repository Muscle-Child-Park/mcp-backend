package com.park.muscle.core.trainer.application;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.core.trainer.dto.request.ClassRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainerService {

    public String completeSignUp(final Trainer trainer) {
        return null;
    }

    public boolean approveMemberRegistration(final Long trainerId, final Long memberId,
                                             final ClassRegistrationRequest classRegistrationRequest) {
        return false;
    }

    public Member getMemberInfo(final Long trainerId, final Long memberId) {
        return null;
    }
}
