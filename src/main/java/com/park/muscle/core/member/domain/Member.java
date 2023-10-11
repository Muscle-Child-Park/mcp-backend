package com.park.muscle.core.member.domain;

import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.global.entity.BaseEntity;
import com.park.muscle.global.enumerate.SocialType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    @Column(nullable = false)
    private String socialId;

    @Embedded
    private Name name;

    @Enumerated(EnumType.STRING)
    private Role role;

    protected Member() {
    }

    @Builder
    public Member(SocialType socialType, String socialId, Name name, Role role) {
        this.socialType = socialType;
        this.socialId = socialId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name) {
        this.name = Name.from(name);
    }
}
