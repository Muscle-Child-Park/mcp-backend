package com.park.muscle.member.command.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Nickname {

    @Column(name = "nickname")
    private String value;

    protected Nickname() {
    }

    public Nickname(String value) {
        this.value = value;
    }
}
