package com.park.muscle.core.jwt.exception;

import com.park.muscle.global.exception.ErrorCode;

public class InvalidTokenException extends JwtException{
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
