package com.park.muscle.core.course.domain;

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
import lombok.Getter;

@Getter
@Entity
public class Course extends BaseEntity {

    @Id
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

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons = new ArrayList<>();

    public void changeTrainer(Trainer trainer) {
        this.trainer = trainer;
        trainer.getCourses().add(this);
    }

    public void changeMember(Member member) {
        this.member = member;
        member.getCourses().add(this);
    }
}
