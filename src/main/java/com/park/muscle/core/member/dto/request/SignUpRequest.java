package com.park.muscle.core.member.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpRequest {

    @NotBlank(message = "소셜 UID는 반드시 존재해야 합니다.")
    private String socialId;

    @NotBlank(message = "소셜 타입은 반드시 존재해야 합니다.")
    private String socialType;

    @NotBlank(message = "이름은 반드시 존재해야 합니다.")
    private String name;

    private SignUpRequest() {
    }

    @Builder
    private SignUpRequest(final String socialId, final String socialType, final String name) {
        this.socialId = socialId;
        this.socialType = socialType;
        this.name = name;
    }
}
