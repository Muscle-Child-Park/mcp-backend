package com.park.muscle.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "값이 올바르지 않습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C002", "지원하지 않는 Http Method 입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C003", "서버 에러"),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "C004", "입력 값의 타입이 올바르지 않습니다."),
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN, "C005", "접근이 거부 되었습니다."),
    RESOURCE_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "R002", "인증이 필요합니다. 로그인을 해주세요."),
    RESOURCE_FORBIDDEN(HttpStatus.FORBIDDEN, "R001", "해당 리소스에 대한 권한이 없습니다."),

    // Member
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "M001", "해당 id를 가진 멤버를 찾을 수 없습니다."),
    INVALID_NAME_LENGTH(HttpStatus.BAD_REQUEST, "M002", "이름의 길이는 10자를 넘길 수 없습니다."),
    SOCIAL_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "M003", "올바른 소셜 타입을 찾을 수 없습니다."),

    // JWT
    REFRESH_JWT_EXPIRED(HttpStatus.UNAUTHORIZED, "J003", "만료된 리프레시 토큰입니다."),

    // Exercise
    EXERCISE_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "E001", "올바른 운동 타입을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return this.status.value();
    }
}
