package com.park.muscle.core.member.domain;

import com.park.muscle.core.member.exception.RoleNotFoundException;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ROLE_MEMBER("member"),
    ROLE_TRAINER("trainer");

    private final String authority;

    public static Role getRole(String authority) {
        return Arrays.stream(values())
                .filter(role -> role.authority.equalsIgnoreCase(authority))
                .findAny()
                .orElseThrow(RoleNotFoundException::new);
    }
}
