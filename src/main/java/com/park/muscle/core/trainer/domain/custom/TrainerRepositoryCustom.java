package com.park.muscle.core.trainer.domain.custom;

import com.park.muscle.core.member.domain.Member;

public interface TrainerRepositoryCustom {
    Member getMemberInfo(Long trainerId, Long memberId);
}
