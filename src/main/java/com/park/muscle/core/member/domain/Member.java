package com.park.muscle.core.member.domain;

import com.park.muscle.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    @Column(nullable = false)
    private String socialId;

    @Embedded
    private Phone phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    protected Member() {
    }

    @Builder
    public Member(final SocialType socialType, final String socialId, final Phone phone, final Role role) {
        this.socialType = socialType;
        this.socialId = socialId;
        this.phone = phone;
        this.role = role;
    }
}
