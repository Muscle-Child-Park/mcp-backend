package com.park.muscle.core.member.dto.response;

import com.park.muscle.core.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SignUpResponse {

    @Schema(description = "member의 고유 ID")
    private final String memberId;

    @Schema(description = "member의 고유 TAG")
    private final String memberTag;

    public SignUpResponse(Member member) {
        this.memberId = member.getId().toString();
        this.memberTag = member.getUniqueTag().formattedId();
    }
}