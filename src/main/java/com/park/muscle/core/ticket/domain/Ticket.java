package com.park.muscle.core.ticket.domain;

import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.global.entity.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Ticket extends BaseEntity {

    @Id
    @Column(name = "TICKET_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private int totalQuantity;

    @Column(nullable = false)
    private int leftQuantity;

    @Column(nullable = false)
    private boolean accepted;

    @OneToMany(mappedBy = "ticket")
    private List<Lesson> lessons = new ArrayList<>();

    @Builder
    public Ticket(Trainer trainer, Member member, int totalQuantity) {
        this.trainer = trainer;
        this.member = member;
        this.totalQuantity = totalQuantity;
        this.leftQuantity = totalQuantity;
        this.accepted = false;
    }

    public void changeTrainer(Trainer trainer) {
        this.trainer = trainer;
        trainer.getTickets().add(this);
    }

    public void changeMember(Member member) {
        this.member = member;
        member.getTickets().add(this);
    }

    public void usedTicket() {
        this.leftQuantity--;
    }

    public int currentLeftQuantity() {
        return leftQuantity;
    }

    public void accept() {
        this.accepted = true;
        trainer.getTickets().add(this);
        member.getTickets().add(this);
    }
}
