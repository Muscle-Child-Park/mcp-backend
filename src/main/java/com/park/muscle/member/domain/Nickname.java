package com.park.muscle.member.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Nickname {

    @Column(name = "nickname")
    private String value;

    protected Nickname() {
    }

    private Nickname(String value) {
        this.value = value;
    }

    public static Nickname from(final String nickname) {
        // TODO need nickname validation
        return new Nickname(nickname);
    }
}