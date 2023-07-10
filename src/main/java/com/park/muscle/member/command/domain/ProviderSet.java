package com.park.muscle.member.command.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProviderSet {

    @Column(name = "provider")
    private String value;

    protected ProviderSet() {
    }

    public ProviderSet(String value) {
        this.value = value;
    }
}
