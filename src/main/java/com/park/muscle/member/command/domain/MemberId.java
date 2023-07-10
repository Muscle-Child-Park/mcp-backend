package com.park.muscle.member.command.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;

public class MemberId implements Serializable {

    @Column(name = "member_id")
    @GeneratedValue
    private Long id;

    protected MemberId() {
    }

    public MemberId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MemberId memberId = (MemberId) obj;
        return Objects.equals(id, memberId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static MemberId of(Long id) {
        return new MemberId(id);
    }
}
