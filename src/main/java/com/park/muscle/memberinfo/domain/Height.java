package com.park.muscle.memberinfo.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Height {

    @Column(name = "height")
    private String value;

    protected Height() {
    }

    private Height(String value) {
        this.value = value;
    }

    public static Height from(String height) {
        // TODO: need validation
        return new Height(height);
    }
}