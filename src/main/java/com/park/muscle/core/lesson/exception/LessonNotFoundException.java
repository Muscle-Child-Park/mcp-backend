package com.park.muscle.core.lesson.exception;

import com.park.muscle.global.exception.BusinessException;
import com.park.muscle.global.exception.ErrorCode;

public class LessonNotFoundException extends BusinessException {
    public LessonNotFoundException() {
        super(ErrorCode.LESSON_NOT_FOUND);
    }
}
