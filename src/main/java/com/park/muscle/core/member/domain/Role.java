package com.park.muscle.core.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ROLE_MEMBER("member");

    private final String authority;
}
