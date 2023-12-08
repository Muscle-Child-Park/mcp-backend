package com.park.muscle.core.personalexercise.exception;

import com.park.muscle.global.exception.BusinessException;
import com.park.muscle.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonalExerciseNotFoundException extends BusinessException {
    public PersonalExerciseNotFoundException() {
        super(ErrorCode.PERSONAL_EXERCISE_NOT_FOUND);
    }
}
