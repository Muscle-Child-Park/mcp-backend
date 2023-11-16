package com.park.muscle.core.exercise.exception;

import com.park.muscle.global.exception.BusinessException;
import com.park.muscle.global.exception.ErrorCode;

public class ExerciseTypeNotFoundException extends BusinessException {

    public ExerciseTypeNotFoundException() {
        super(ErrorCode.EXERCISE_TYPE_NOT_FOUND);
    }
}
