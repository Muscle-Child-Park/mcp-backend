package com.park.muscle.core.member.dto.response;

import com.park.muscle.core.member.domain.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class MemberSimpleInfoResponse {

    @ApiModelProperty("member의 고유 Id")
    private final long memberId;

    public MemberSimpleInfoResponse(Member member) {
        this.memberId = member.getId();
    }
}