package com.park.muscle.core;

import com.park.muscle.core.member.domain.Role;

public interface User {
    Long getId();

    Role getRole();

    Long getUniqueTagId();
}
