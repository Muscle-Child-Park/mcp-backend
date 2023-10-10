package com.park.muscle.global.util;

import static com.park.muscle.core.jwt.application.JwtTokenProvider.REFRESH_TOKEN_EXPIRE_LENGTH_MS;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

public class CookieUtil {

    private CookieUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void setRefreshCookie(HttpHeaders httpHeaders, String refreshToken) {

        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .maxAge(REFRESH_TOKEN_EXPIRE_LENGTH_MS)
                .path("/")
                .sameSite("None")
                .build();

        httpHeaders.add(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
