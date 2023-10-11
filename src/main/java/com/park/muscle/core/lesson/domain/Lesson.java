package com.park.muscle.core.lesson.domain;

import com.park.muscle.core.course.domain.Course;
import com.park.muscle.core.exercise.domain.ExerciseDiary;
import com.park.muscle.global.entity.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;

@Getter
@Entity
public class Lesson extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXERCISE_DIARY_ID", nullable = false)
    private ExerciseDiary exerciseDiary;

    @Column(nullable = false)
    private LocalDateTime lessonDate;

    @Column(nullable = false)
    private String timeSlot;

    private String memoir;
    private String feedback;

}
