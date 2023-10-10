package com.park.muscle.core.member.exception;

public class MemberNotFoundException extends RuntimeException {

    private static final String MESSAGE = "회원을 찾을 수 없습니다.";

    public MemberNotFoundException() {
        super(MESSAGE);
    }
}
