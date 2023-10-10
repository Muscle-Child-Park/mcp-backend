package com.park.muscle.core.member.exception;

import com.park.muscle.global.exception.BusinessException;
import com.park.muscle.global.exception.ErrorCode;

public class SocialTypeNotFoundException extends BusinessException {

    public SocialTypeNotFoundException() {
        super(ErrorCode.SOCIAL_TYPE_NOT_FOUND);
    }
}
