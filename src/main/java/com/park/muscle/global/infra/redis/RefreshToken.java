package com.park.muscle.global.infra.redis;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 1000L * 60 * 60 * 24 * 14)
public class RefreshToken {

    @Id
    private final Long memberId;

    /**
     * 사용자가 가지고 있는 refreshToken
     */
    @Indexed
    private final String refreshToken;

    public RefreshToken(Long memberId, String refreshToken) {
        this.memberId = memberId;
        this.refreshToken = refreshToken;
    }
}
