package com.park.muscle.global.infra.redis;

import static com.park.muscle.global.exception.ErrorCode.REFRESH_JWT_EXPIRED;

import com.park.muscle.core.jwt.exception.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * refreshToken 관리를 위한 Redis 관련 서비스.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void saveRefreshToken(Long memberId, String refreshToken) {
        refreshTokenRepository.save(new RefreshToken(memberId, refreshToken));
    }

    @Transactional(readOnly = true)
    public Long findMemberByToken(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new JwtException(REFRESH_JWT_EXPIRED));
        return token.getUserId();
    }

    private final RedisTemplate<String, Object> redisTemplate;

    public void redisString() {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set("redisHello", "redisHello");
        String redis = (String) operations.get("redisHello");
        log.info(redis);
    }
}
