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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unique_tag_id")
    private Long id;

    @Column(name = "unique_tag")
    private UUID uniqueTag;

    public UniqueTag() {
        this.uniqueTag = UUID.randomUUID();
    }

    public String getUniqueTag() {
        return uniqueTag.toString();
    }
}
