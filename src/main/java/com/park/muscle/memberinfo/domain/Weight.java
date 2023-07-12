package com.park.muscle.memberinfo.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Weight {

    @Column(name = "weight")
    private String value;

    protected Weight() {
    }

    private Weight(String value) {
        this.value = value;
    }

    public static Weight from(final String weight) {
        // TODO need validation
        return new Weight(weight);
    }
}