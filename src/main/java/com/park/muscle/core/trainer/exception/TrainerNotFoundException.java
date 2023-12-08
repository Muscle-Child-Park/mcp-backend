package com.park.muscle.core.trainer.exception;

import com.park.muscle.global.exception.BusinessException;
import com.park.muscle.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TrainerNotFoundException extends BusinessException {
    public TrainerNotFoundException() {
        super(ErrorCode.TRAINER_NOT_FOUND);
    }
}
