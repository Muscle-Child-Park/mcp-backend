package com.park.muscle.core.member.dto.response;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.member.domain.Name;
import com.park.muscle.core.onboarding.domain.Onboarding;
import lombok.Getter;

@Getter
public class ProfileResponse {

    private final Name name;

    public ProfileResponse(Member member, Onboarding onboarding) {
        this.name = member.getName();
    }

}
