package com.park.muscle.global.entity;

import com.park.muscle.core.member.domain.Role;

public interface Users {
    Long getId();

    Role getRole();

    Long getUniqueTagId();
}
