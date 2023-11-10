package com.park.muscle.core.member.dto.response;

import com.park.muscle.core.member.domain.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class SignUpResponse {

    @ApiModelProperty("member의 고유 ID")
    private final String memberId;

    @ApiModelProperty("member의 고유 TAG")
    private final String memberTag;

    public SignUpResponse(Member member) {
        this.memberId = member.getId().toString();
        this.memberTag = member.getUniqueTag().getFormattedId();
    }
}