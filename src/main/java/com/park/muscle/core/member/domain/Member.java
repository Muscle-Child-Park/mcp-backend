package com.park.muscle.core.member.domain;

import com.park.muscle.core.lesson.domain.ExerciseDiary;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.uniquetag.domain.UniqueTag;
import com.park.muscle.global.entity.BaseEntity;
import com.park.muscle.global.enumerate.SocialType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @OneToMany(mappedBy = "member")
    private final List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private final List<ExerciseDiary> diaries = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    @Column(nullable = false)
    private String socialId;

    @Embedded
    private Name name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    private UniqueTag uniqueTag;

    @Builder
    public Member(SocialType socialType, String socialId, Name name, Role role) {
        this.socialType = socialType;
        this.socialId = socialId;
        this.name = name;
        this.role = role;
        this.uniqueTag = UniqueTag.builder().member(this).build();
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name) {
        this.name = Name.from(name);
    }

    public void addDiaries(ExerciseDiary diary) {
        diary.setMember(this);
        this.diaries.add(diary);
    }
}
