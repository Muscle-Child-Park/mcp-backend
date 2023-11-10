package com.park.muscle.core.uniquetag.domain;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class UniqueTag {
    private final static int LIMIT_LENGTH = 99999;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unique_tag_id")
    private Long id;

    @Column(name = "unique_tag")
    private String uniqueTag;

    public UniqueTag() {
        this.uniqueTag = generateUniqueTag();
    }

    public String getFormattedId() {
        String formattedNumber = String.format("#%d", id);
        if (isFormatted()) {
            formattedNumber = String.format("#%05d", id);
            return formattedNumber;
        }
        return formattedNumber;
    }

    private boolean isFormatted() {
        return id <= LIMIT_LENGTH;
    }

    private String generateUniqueTag() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public String removeHash(String clientTag) {
        return clientTag.replace("#", "");
    }
}
