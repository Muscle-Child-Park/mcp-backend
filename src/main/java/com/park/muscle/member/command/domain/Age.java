package com.park.muscle.member.command.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Age {

    @Column(name = "age")
    private String value;

    protected Age() {
    }

    public Age(String value) {
        this.value = value;
    }
}
