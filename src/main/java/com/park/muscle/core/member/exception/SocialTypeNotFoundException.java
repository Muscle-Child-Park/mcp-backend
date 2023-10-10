package com.park.muscle.core.member.exception;

public class SocialTypeNotFoundException extends RuntimeException {

    private static final String MESSAGE = "올바른 소셜 타입을 찾을 수 없습니다.";

    public SocialTypeNotFoundException() {
        super(MESSAGE);
    }
}
