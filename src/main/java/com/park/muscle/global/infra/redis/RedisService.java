package com.park.muscle.global.infra.redis;

import static com.park.muscle.global.exception.ErrorCode.MEMBER_NOT_FOUND;
import static com.park.muscle.global.exception.ErrorCode.REFRESH_JWT_EXPIRED;

import com.park.muscle.core.jwt.exception.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * refreshToken 관리를 위한 Redis 관련 서비스.
 */
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
        return token.getMemberId();
    }
}
