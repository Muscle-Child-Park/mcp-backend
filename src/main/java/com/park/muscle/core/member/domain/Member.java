package com.park.muscle.core.member.domain;

import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.exercise.domain.ExerciseDiary;
import com.park.muscle.core.onboarding.domain.Onboarding;
import com.park.muscle.core.personalexercise.domain.PersonalExercise;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.uniquetag.domain.UniqueTag;
import com.park.muscle.global.entity.BaseEntity;
import com.park.muscle.global.enumerate.SocialType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "social_type")
    private SocialType socialType;

    @Column(name = "social_id", nullable = false)
    private String socialId;

    @Embedded
    private Name name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Onboarding onboarding;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Exercise> exercises = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "member", orphanRemoval = true)
    private final List<Ticket> tickets = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ExerciseDiary> diaries = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PersonalExercise> personalExercises = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private UniqueTag uniqueTag;

    @Builder
    public Member(SocialType socialType, String socialId, Name name, Role role) {
        this.socialType = socialType;
        this.socialId = socialId;
        this.name = name;
        this.role = role;
        UniqueTag uniqueTag = UniqueTag.builder().build();
        uniqueTag.updateMember(this);
        this.uniqueTag = uniqueTag;
    }

    public String getMemberName() {
        return name.getValue();
    }

    public void updateName(String name) {
        this.name = Name.from(name);
    }

    public void addPersonalExercises(PersonalExercise personalExercises) {
        this.personalExercises.add(personalExercises);
    }
}
