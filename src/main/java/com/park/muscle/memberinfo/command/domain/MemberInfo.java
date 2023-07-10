package com.park.muscle.memberinfo.command.domain;

import com.park.muscle.member.command.domain.Member;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class MemberInfo {

    @EmbeddedId
    private MemberInfoId id;
    @Embedded
    private Height height;
    @Embedded
    private Weight weight;
    @Embedded
    private InBody inBody;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected MemberInfo(){}

    public MemberInfo(Height height, Weight weight, InBody inBody){
        this.height = height;
        this.weight = weight;
        this.inBody = inBody;
    }

    public MemberInfoId getId() {
        return id;
    }

    public Height getHeight() {
        return height;
    }

    public Weight getWeight() {
        return weight;
    }

    public InBody getInBody() {
        return inBody;
    }

    public Member getMember() {
        return member;
    }
}
