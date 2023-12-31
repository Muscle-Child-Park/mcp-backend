package com.park.muscle.core.trainer.domain;

import com.park.muscle.core.member.exception.InvalidNameLengthException;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Tag(name = "Trainer Name")
@Embeddable
public class Name {
    private static final int LIMIT_LENGTH = 10;

    @Column(name = "name")
    private String value;

    protected Name() {
    }

    private Name(String value) {
        this.value = value;
    }

    public static Name from(String name) {
        validate(name);
        return new Name(name);
    }

    public String getName() {
        return this.value;
    }

    private static void validate(String name) {
        if (name.length() > LIMIT_LENGTH) {
            throw new InvalidNameLengthException();
        }
    }
}
