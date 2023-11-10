package com.park.muscle.core.uniquetag.domain;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.trainer.domain.Trainer;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class UniqueTag {
    private final static int LIMIT_LENGTH = 99999;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unique_tag_id")
    private Long id;

    @Column(name = "unique_tag")
    private String uniqueTag;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @Builder
    public UniqueTag(Member member, Trainer trainer) {
        this.uniqueTag = generateUniqueTag();
        this.member = member;
        this.trainer = trainer;
    }

    public String formattedId() {
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
