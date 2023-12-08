package com.park.muscle.core.lesson.exception;

import com.park.muscle.global.exception.BusinessException;
import com.park.muscle.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LessonNotFoundException extends BusinessException {
    public LessonNotFoundException() {
        super(ErrorCode.LESSON_NOT_FOUND);
    }
}
