package com.park.muscle.core.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ROLE_MEMBER("member"),
    ROLE_TRAINER("trainer");

    private final String authority;
}
