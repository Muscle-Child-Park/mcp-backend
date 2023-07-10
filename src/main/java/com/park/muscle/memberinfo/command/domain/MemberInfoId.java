package com.park.muscle.memberinfo.command.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;

public class MemberInfoId implements Serializable {

    @Column(name = "member_Info_id")
    @GeneratedValue
    private Long id;

    protected MemberInfoId() {
    }

    public MemberInfoId(Long id) {
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
        MemberInfoId MemberInfoId = (MemberInfoId) obj;
        return Objects.equals(id, MemberInfoId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static MemberInfoId of(Long id) {
        return new MemberInfoId(id);
    }
}
