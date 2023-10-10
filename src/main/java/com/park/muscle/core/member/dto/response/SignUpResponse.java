package com.park.muscle.core.member.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class SignUpResponse {

    @ApiModelProperty("member의 고유 Id")
    private final long memberId;

    public SignUpResponse(Long memberId) {
        this.memberId = memberId;
    }
}