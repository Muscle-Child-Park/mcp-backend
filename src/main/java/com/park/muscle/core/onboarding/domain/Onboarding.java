package com.park.muscle.core.onboarding.domain;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Builder;

@Entity
public class Onboarding extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "onboarding_id")
    private Long id;

    private String firstPurpose;

    private String secondPurpose;

    private String balance;

    private String interest;

    private String lifeStyle;

    private String eatingHabit;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    protected Onboarding() {
    }

    @Builder
    private Onboarding(final String firstPurpose, final String secondPurpose, final String balance,
                       final String interest, final String lifeStyle,
                       final String eatingHabit, final Member member) {
        this.firstPurpose = firstPurpose;
        this.secondPurpose = secondPurpose;
        this.balance = balance;
        this.interest = interest;
        this.lifeStyle = lifeStyle;
        this.eatingHabit = eatingHabit;
        this.member = member;
    }
}
