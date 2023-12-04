package com.park.muscle.core.exercise.exception;

import com.park.muscle.global.exception.BusinessException;
import com.park.muscle.global.exception.ErrorCode;

public class ExerciseNotFoundException extends BusinessException {
    public ExerciseNotFoundException() {
        super(ErrorCode.EXERCISE_NOT_FOUND);
    }
}
