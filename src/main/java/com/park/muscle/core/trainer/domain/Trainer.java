package com.park.muscle.core.trainer.domain;

import com.park.muscle.core.course.domain.Course;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.global.entity.BaseEntity;
import com.park.muscle.global.enumerate.SocialType;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Trainer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    @Column(nullable = false)
    private String social;

    @Column(nullable = false)
    private String name;

    private String trainerTag;

    @OneToMany(mappedBy = "trainer", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<Course> courses;

    @OneToMany(mappedBy = "trainer", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<Member> members;

    @OneToMany(mappedBy = "trainer", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<DayOff> dayOffs;

}
