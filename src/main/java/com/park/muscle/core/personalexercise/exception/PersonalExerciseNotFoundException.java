package com.park.muscle.core.personalexercise.exception;

import com.park.muscle.global.exception.BusinessException;
import com.park.muscle.global.exception.ErrorCode;

public class PersonalExerciseNotFoundException extends BusinessException {
    public PersonalExerciseNotFoundException() {
        super(ErrorCode.PERSONAL_EXERCISE_NOT_FOUND);
    }
}
