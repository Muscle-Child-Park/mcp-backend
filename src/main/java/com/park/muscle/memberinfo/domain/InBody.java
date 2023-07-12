package com.park.muscle.memberinfo.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InBody {

    @Column(name = "inBody")
    private String value;

    protected InBody() {
    }

    private InBody(String value) {
        this.value = value;
    }

    public static InBody from(String inBody) {
        // TODO need validation
        return new InBody(inBody);
    }
}