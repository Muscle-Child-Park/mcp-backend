package com.park.muscle.core.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class LoginResponse {
    @Schema(description = "발급된 액세스 토큰")
    private final String accessToken;

    @Schema(description = "발급된 리프레쉬 토큰")
    private final String refreshToken;

    @Schema(description = "멤버의 id만 반환되는 dto")
    private final SignUpResponse member;

    public LoginResponse(String accessToken, String refreshToken, SignUpResponse member) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.member = member;
    }
}
