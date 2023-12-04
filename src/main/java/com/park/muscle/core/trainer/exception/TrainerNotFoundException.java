package com.park.muscle.core.trainer.exception;

import com.park.muscle.global.exception.BusinessException;
import com.park.muscle.global.exception.ErrorCode;

public class TrainerNotFoundException extends BusinessException {
    public TrainerNotFoundException() {
        super(ErrorCode.TRAINER_NOT_FOUND);
    }
}
