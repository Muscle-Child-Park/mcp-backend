package com.park.muscle.core.exercise.domain;

import com.park.muscle.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "exercise_diary")
@NoArgsConstructor
public class ExerciseDiary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_diary_id")
    private Long id;

    @Column(nullable = false)
    private String memo;

    @Builder
    public ExerciseDiary(String memo) {
        this.memo = memo;
    }

    public void updateLog(final String log) {
        this.memo = log;
    }
}
