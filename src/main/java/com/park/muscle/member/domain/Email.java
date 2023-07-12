package com.park.muscle.member.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Email {

    @Column(name = "email")
    private String value;

    protected Email() {
    }

    private Email(String value) {
        this.value = value;
    }

    public static Email from(final String email) {
        // TODO email validation
        return new Email(email);
    }
}