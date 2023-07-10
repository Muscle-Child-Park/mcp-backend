package com.park.muscle.member.command.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Email {

    @Column(name = "email")
    private String value;

    protected Email() {
    }

    public Email(String value) {
        this.value = value;
    }
}
