package com.park.muscle.core.jwt.dto;

import com.park.muscle.global.entity.Users;
import java.io.Serializable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SessionUser implements Serializable {

    private final Long id;
    private final String authority;

    public SessionUser(Users users) {
        this.id = users.getId();
        this.authority = users.getRole().getAuthority();
    }
}
