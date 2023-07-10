package com.park.muscle.member.command.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProviderId {

    @Column(name = "provider_id")
    private String value;

    protected ProviderId(){}

    public ProviderId(String value){
        this.value = value;
    }
}
