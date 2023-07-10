package com.park.muscle.memberinfo.command.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InBody {
    @Column(name = "inBody")
    private String value;

    protected InBody(){}

    public InBody(String value){
        this.value = value;
    }
}
