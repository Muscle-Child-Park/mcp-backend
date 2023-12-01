package com.park.muscle.core.onboarding.domain;

import static com.park.muscle.core.onboarding.dto.OnboardingRequest.UpdateRequest;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "onboarding")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Onboarding extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "onboarding_id")
    private Long id;

    @Column(name = "first_purpose")
    private String firstPurpose;

    @Column(name = "second_purpose")
    private String secondPurpose;

    @Column(name = "balance")
    private String balance;

    @Column(name = "interest")
    private String interest;

    @Column(name = "life_style")
    private String lifeStyle;

    @Column(name = "eating_habit")
    private String eatingHabit;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    private Onboarding(String firstPurpose, String secondPurpose, String balance, String interest, String lifeStyle,
                       String eatingHabit, String name, Member member) {
        this.firstPurpose = firstPurpose;
        this.secondPurpose = secondPurpose;
        this.balance = balance;
        this.interest = interest;
        this.lifeStyle = lifeStyle;
        this.eatingHabit = eatingHabit;
        this.name = name;
        this.member = member;
    }

    public void updateOnboarding(final UpdateRequest request) {
        this.firstPurpose = request.getFirstPurpose();
        this.secondPurpose = request.getSecondPurpose();
        this.balance = request.getBalance();
        this.interest = request.getInterest();
        this.lifeStyle = request.getLifeStyle();
        this.eatingHabit = request.getEatingHabit();
        this.name = request.getName();
    }
}
