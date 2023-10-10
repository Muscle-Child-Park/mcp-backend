package com.park.muscle.core.member.exception;

public class InvalidNameLengthException extends RuntimeException {

    private static final String MESSAGE = "이름의 길이는 10자를 넘길 수 없습니다.";

    public InvalidNameLengthException() {
        super(MESSAGE);
    }
}
