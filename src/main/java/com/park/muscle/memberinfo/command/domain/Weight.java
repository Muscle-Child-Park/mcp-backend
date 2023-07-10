package com.park.muscle.memberinfo.command.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Weight {

    @Column(name = "weight")
    private String value;

    protected Weight(){}

    public Weight(String value){
        this.value = value;
    }
}
