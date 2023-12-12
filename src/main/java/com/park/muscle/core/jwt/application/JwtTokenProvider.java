package com.park.muscle.core.jwt.application;

import com.park.muscle.core.jwt.JwtProperties;
import com.park.muscle.core.jwt.dto.SessionUser;
import com.park.muscle.core.jwt.exception.InvalidTokenException;
import com.park.muscle.core.member.domain.Role;
import com.park.muscle.core.uniquetag.domain.UniqueTag;
import com.park.muscle.core.uniquetag.domain.UniqueTagRepository;
import com.park.muscle.core.uniquetag.exception.TagNotFoundException;
import com.park.muscle.global.entity.Users;
import com.park.muscle.global.infra.redis.RefreshToken;
import com.park.muscle.global.infra.redis.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final Long ACCESS_TOKEN_EXPIRE_LENGTH_MS = JwtProperties.ACCESS_TOKEN_EXPIRATION;
    public static final Long REFRESH_TOKEN_EXPIRE_LENGTH_MS = JwtProperties.REFRESH_TOKEN_EXPIRATION;

    private final UniqueTagRepository uniqueTagRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private String secretKey = JwtProperties.SECRET;
    private Key key;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    // AccessToken 사용 Authentication 객체 생성
    public Authentication getAuthentication(String accessToken) {
        Claims claims = extractAllClaims(accessToken); // claims 정보 추출시 유효성 체크
        String role = claims.get("role").toString();
        long id = Long.parseLong(claims.getSubject());

        log.info("현재 claims id : {}", id);
        return new UsernamePasswordAuthenticationToken(getSessionUser(id), "", getGrantedAuthorities(role));
    }

    public String createAccessToken(Long userId, Role role) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_LENGTH_MS);

        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setSubject(String.valueOf(userId))
                .claim("role", role.getAuthority())
                .setIssuer("mcpark")
                .setIssuedAt(now)
                .setExpiration(validity)
                .compact();
    }

    public String createRefreshToken(Long userId) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_LENGTH_MS);

        String refreshToken = Jwts.builder()
                .setIssuer("mcpark")
                .setSubject(userId.toString())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        RefreshToken token = new RefreshToken(userId, refreshToken);
        refreshTokenRepository.save(token);
        return refreshToken;
    }

    private Claims extractAllClaims(String token) {
        return checkClaims(token).getBody();
    }

    private Jws<Claims> checkClaims(String token) {
        try {
            byte[] secretKeyBytes = Base64.getDecoder().decode(secretKey);
            return Jwts.parserBuilder().setSigningKey(secretKeyBytes).build()
                    .parseClaimsJws(token);
        } catch (MalformedJwtException | SignatureException | UnsupportedJwtException e) {
            log.warn("유효하지 않은 토큰입니다.", e);
            throw new JwtException("JWT_INVALID_TOKEN");
        } catch (ExpiredJwtException e) {
            log.warn("만료된 토큰입니다.", e);
            throw new JwtException("JWT_EXPIRED_TOKEN");
        }
    }

    private SessionUser getSessionUser(Long tagId) {
        log.info("session user를 만들기 위해 id : {} 인 사람을 찾습니다.", tagId);
        UniqueTag uniqueTag = uniqueTagRepository.findById(tagId)
                .orElseThrow(TagNotFoundException::new);
        return createSessionUser(uniqueTag);
    }

    private SessionUser createSessionUser(UniqueTag uniqueTag) {
        Users users = (uniqueTag.getTrainer() != null) ? uniqueTag.getTrainer() : uniqueTag.getMember();
        return new SessionUser(users);
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        return grantedAuthorities;
    }

    public boolean isTokenExpirationSafe(String token) {
        Instant expiration = extractAllClaims(token).getExpiration().toInstant();
        Instant now = Instant.now();
        return hasTokenExpMoreThanThreeDays(expiration, now);
    }

    private boolean hasTokenExpMoreThanThreeDays(Instant expiration, Instant now) {
        return (Duration.between(now, expiration).compareTo(Duration.ofDays(3)) >= 0);
    }

    public void saveMemberTokenInRedis(Users users, String refreshToken) {
        refreshTokenRepository.save(new RefreshToken(users.getUniqueTagId(), refreshToken));
    }

    public void saveTrainerTokenInRedis(Users users, String refreshToken) {
        refreshTokenRepository.save(new RefreshToken(users.getUniqueTagId(), refreshToken));
    }

    public String getValidRefreshToken(final Long uniqueTagId) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findById(uniqueTagId);
        if (refreshToken.isEmpty()) {
            throw new InvalidTokenException();
        }
        return refreshToken.get().getRefreshToken();
    }
}
