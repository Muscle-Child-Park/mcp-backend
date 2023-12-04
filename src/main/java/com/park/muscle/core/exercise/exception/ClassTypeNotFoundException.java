package com.park.muscle.core.exercise.exception;

import com.park.muscle.global.exception.BusinessException;
import com.park.muscle.global.exception.ErrorCode;

public class ClassTypeNotFoundException extends BusinessException {
    public ClassTypeNotFoundException() {
        super(ErrorCode.CLASS_TYPE_NOT_FOUND);
    }
}
