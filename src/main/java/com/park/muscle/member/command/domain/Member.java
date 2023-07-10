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

    // TODO: id값 까지 wrapping 할 필요가 있을까요? Member 객체끼리의 비교는 거의 하지 않을것 같다는 생각입니다.(주로 findById를 통해 DB조회를 하지 않을까요?)
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

    // TODO Provider는 Enum타입으로 구분하는것이 맞지 않을까요?
    @Embedded
    private ProviderSet provider;

    @Enumerated(EnumType.STRING)
    private Roles role; // [ADMIN, MEMBER]

    // TODO Member의 생성주기와 MemberInfo의 생성주기가 다른데 CascadeType.ALL로 묶는다면 문제의 소지가 있지 않을까요? orphanRemoval은 어떤 역할을 하는지 궁금하네요!
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private MemberInfo memberInfo;

    protected Member() {
    }

    // TODO 아래와 같은 생성자는 서비스 로직에서 임베디드 도메인들을 생성할 수 밖에 없는 구조 같습니다! 외부에서는 원시타입 혹은 String과 같은 타입으로 주입받고 생성자 내부에서 변환하도록 하는것은 어떨까요?
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
