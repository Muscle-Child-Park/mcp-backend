package com.park.muscle.core.ticket.domain;

import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.reservation.domain.Reservation;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.global.entity.BaseEntity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Ticket extends BaseEntity {

    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int totalQuantity;

    @Column(nullable = false)
    private int leftQuantity;

    @Column(nullable = false)
    private boolean accepted;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "ticket")
    private Reservation reservation;

    @Builder
    public Ticket(Trainer trainer, Member member, int totalQuantity) {
        this.trainer = trainer;
        this.member = member;
        this.totalQuantity = totalQuantity;
        this.leftQuantity = totalQuantity;
        this.accepted = false;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public void changeTrainer(Trainer trainer) {
        this.trainer = trainer;
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

    public void assignToMemberAndTrainer(Member member, Trainer trainer) {
        this.member = member;
        this.trainer = trainer;
        this.accepted = true;
        member.getTickets().add(this);
        trainer.getTickets().add(this);
    }
}
