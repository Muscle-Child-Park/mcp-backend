package com.park.muscle.core.uniquetag.exception;

import com.park.muscle.global.exception.BusinessException;
import com.park.muscle.global.exception.ErrorCode;

public class TagNotFoundException extends BusinessException {
    public TagNotFoundException() {
        super(ErrorCode.UNIQUE_TAG_NOT_FOUND);
    }
}
