package com.park.muscle.core.member.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Phone {

    @Column(name = "phone")
    private String value;

    protected Phone() {
    }

    private Phone(final String value) {
        this.value = value;
    }

    public static Phone from(final String phone) {
        // TODO: phone 인코딩 및 validate 필요
        return new Phone(phone);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Phone phone = (Phone) o;
        return value.equals(phone.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
