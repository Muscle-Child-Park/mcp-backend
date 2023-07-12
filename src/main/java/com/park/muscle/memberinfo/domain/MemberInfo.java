package com.park.muscle.memberinfo.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;

@Entity
public class MemberInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_info_id")
    private Long id;

    @Embedded
    private Height height;

    @Embedded
    private Weight weight;

    @Embedded
    private InBody inBody;

    protected MemberInfo() {
    }

    @Builder
    private MemberInfo(final String height, final String weight, final String inBody) {
        this.height = Height.from(height);
        this.weight = Weight.from(weight);
        this.inBody = InBody.from(inBody);
    }
}