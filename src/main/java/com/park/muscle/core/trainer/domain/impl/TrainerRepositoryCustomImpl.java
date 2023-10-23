package com.park.muscle.core.trainer.domain.impl;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.member.domain.QMember;
import com.park.muscle.core.trainer.domain.custom.TrainerRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrainerRepositoryCustomImpl implements TrainerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Member getMemberInfo(Long trainerId, Long memberId) {
        QMember member = QMember.member;
        Member memberInfo = queryFactory.select(member)
                .from(member)
                .where(
                        member.id.eq(memberId)
                                .and(member.courses.any().trainer.id.eq(trainerId))
                )
                .fetchOne();
        return memberInfo;
    }


}
