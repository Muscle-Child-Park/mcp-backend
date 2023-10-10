package com.park.muscle.core.member.exception;

import com.park.muscle.global.exception.BusinessException;
import com.park.muscle.global.exception.ErrorCode;

public class MemberNotFoundException extends BusinessException {
    public MemberNotFoundException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
