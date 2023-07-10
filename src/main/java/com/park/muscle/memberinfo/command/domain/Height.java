package com.park.muscle.memberinfo.command.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Height {
    @Column(name = "height")
    private String value;

    protected Height(){}

    public Height(String value){
        this.value = value;
    }
}
