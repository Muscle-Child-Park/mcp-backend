package com.park.muscle.core.trainer.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrainerRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


}
