package com.park.muscle.member.command.domain;

import com.park.muscle.memberinfo.command.domain.MemberInfo;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Entity
public class Member {

    @EmbeddedId
    private MemberId id;

    @Embedded
    private Nickname nickname;

    @Embedded
    private Age age;

    @Embedded
    private Email email;

    @Embedded
    private ProviderId providerId;

    @Embedded
    private ProviderSet provider;

    @Enumerated(EnumType.STRING)
    private Roles role; // [ADMIN, MEMBER]

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private MemberInfo memberInfo;

    protected Member() {
    }

    public Member(Nickname nickname, Age age, Email email, ProviderId providerId, ProviderSet provider, Roles role) {
        this.nickname = nickname;
        this.age = age;
        this.email = email;
        this.providerId = providerId;
        this.provider = provider;
        this.role = role;
    }

    public MemberId getId() {
        return id;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public Age getAge() {
        return age;
    }

    public Email getEmail() {
        return email;
    }

    public ProviderId getProviderId() {
        return providerId;
    }

    public ProviderSet getProvider() {
        return provider;
    }

    public Roles getRole() {
        return role;
    }

    public MemberInfo getMemberInfo() {
        return memberInfo;
    }
}
