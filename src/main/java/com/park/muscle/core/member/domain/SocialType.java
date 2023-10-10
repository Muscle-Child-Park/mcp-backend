package com.park.muscle.core.member.domain;

import com.park.muscle.core.member.exception.SocialTypeNotFoundException;
import java.util.Arrays;

public enum SocialType {

    KAKAO,
    GOOGLE,
    APPLE;

    public static SocialType findType(String socialType) {
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(socialType))
                .findAny()
                .orElseThrow(SocialTypeNotFoundException::new);
    }
}
