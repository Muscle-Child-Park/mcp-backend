package com.park.muscle.core.jwt.exception;

import com.park.muscle.global.exception.ErrorCode;

public class JwtException extends RuntimeException {

    private final ErrorCode errorCode;

    public JwtException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
