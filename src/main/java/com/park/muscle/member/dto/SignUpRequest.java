package com.park.muscle.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpRequest {

    private String providerId;
    private String email;
    private String provider;

    private SignUpRequest() {
    }

    @Builder
    private SignUpRequest(final String providerId, final String email, final String provider) {
        this.providerId = providerId;
        this.email = email;
        this.provider = provider;
    }
}