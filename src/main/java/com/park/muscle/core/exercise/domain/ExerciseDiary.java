package com.park.muscle.core.exercise.domain;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.global.entity.BaseEntity;
import java.time.LocalDateTime;
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
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "EXERCISE_DIARY")
public class ExerciseDiary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXERCISE_DIARY_ID")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String memo;

    @Column(nullable = false)
    private LocalDateTime excerciseDate;

    @Column(nullable = false)
    private String timeSlot;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "exerciseDiary")
    List<Exercise> exercises = new ArrayList<>();
}
