package com.park.muscle.core.exercise.exception;

import com.park.muscle.global.exception.BusinessException;
import com.park.muscle.global.exception.ErrorCode;

public class ExerciseDiaryNotFoundException extends BusinessException {

    public ExerciseDiaryNotFoundException() {
        super(ErrorCode.EXERCISE_DIARY_NOT_FOUND);
    }
}
