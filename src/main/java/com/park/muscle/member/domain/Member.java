package com.park.muscle.member.domain;

import com.park.muscle.memberinfo.domain.MemberInfo;
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
import javax.persistence.OneToOne;
import lombok.Builder;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Embedded
    private Nickname nickname;

    @Embedded
    private Age age;

    @Embedded
    private Email email;

    private String providerId;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_info_id")
    private MemberInfo memberInfo;

    protected Member() {
    }

    @Builder
    private Member(final String nickname, final String age, final String email, final String providerId,
                   final String provider, final Role role, final MemberInfo memberInfo) {
        this.nickname = Nickname.from(nickname);
        this.age = Age.from(age);
        this.email = Email.from(email);
        this.providerId = providerId;
        this.provider = Provider.valueOf(provider);
        this.role = role;
        this.memberInfo = memberInfo;
    }
}