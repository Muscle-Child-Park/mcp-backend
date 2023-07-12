package com.park.muscle.memberinfo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberInfoRequest {

    private String age;
    private String height;
    private String weight;
    private String inBody;
    private String nickname;

    private MemberInfoRequest() {
    }

    @Builder
    private MemberInfoRequest(final String age, final String height, final String weight, final String inBody,
                             final String nickname) {
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.inBody = inBody;
        this.nickname = nickname;
    }
}