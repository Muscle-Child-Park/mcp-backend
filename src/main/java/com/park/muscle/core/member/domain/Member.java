package com.park.muscle.core.member.domain;

import com.park.muscle.core.course.domain.Course;
import com.park.muscle.core.exercise.domain.ExerciseDiary;
import com.park.muscle.global.entity.BaseEntity;
import com.park.muscle.global.enumerate.SocialType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    @Column(nullable = false)
    private String socialId;

    @OneToMany(mappedBy = "member")
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ExerciseDiary> diaries = new ArrayList<>();

    @Embedded
    private Name name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(SocialType socialType, String socialId, Name name, Role role) {
        this.socialType = socialType;
        this.socialId = socialId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name) {
        this.name = Name.from(name);
    }

    public void addDiaries(ExerciseDiary diary) {
        diary.setMember(this);
        this.diaries.add(diary);
    }
}
