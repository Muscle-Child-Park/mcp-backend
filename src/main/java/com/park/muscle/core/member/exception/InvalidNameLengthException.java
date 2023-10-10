package com.park.muscle.core.member.exception;

import com.park.muscle.global.exception.BusinessException;
import com.park.muscle.global.exception.ErrorCode;

public class InvalidNameLengthException extends BusinessException {

    public InvalidNameLengthException() {
        super(ErrorCode.INVALID_NAME_LENGTH);
    }
}
