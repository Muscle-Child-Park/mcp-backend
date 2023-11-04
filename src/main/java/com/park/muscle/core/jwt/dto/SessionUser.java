package com.park.muscle.core.jwt.dto;

import com.park.muscle.core.member.domain.Member;
import java.io.Serializable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SessionUser implements Serializable {

    private final Long uuid;
    private final String authority;

    public SessionUser(Member member) {
        this.uuid = member.getId();
        this.authority = member.getRole().getAuthority();
    }
}
