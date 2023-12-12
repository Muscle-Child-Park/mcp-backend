package com.park.muscle.core.uniquetag.domain;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.global.entity.User;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "unique_tag")
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
    public UniqueTag() {
        this.uniqueTag = generateUniqueTag();
    }

    public User getUser() {
        if (member == null) {
            return trainer;
        }
        return member;
    }

    public void updateMember(Member member) {
        this.member = member;
    }

    public void updateTrainer(Trainer trainer) {
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

}
