package com.park.muscle.core.personalexercise.domain;

import com.park.muscle.core.exercise.domain.ClassType;
import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.exercise.domain.ExerciseDiary;
import com.park.muscle.core.personalexercise.dto.request.PersonalExerciseRequest.Update;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "personal_exercise")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personal_exercise_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "lesson_date", nullable = false)
    private LocalDateTime lessonDate;

    @Column(name = "time_slot", nullable = false)
    private String timeSlot;

    @Column(name = "completion_toggle")
    private boolean completionToggle;

    @Column(name = "class_type")
    private ClassType classType;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Exercise> exercises = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "exercise_diary_id")
    private ExerciseDiary exerciseDiary;

    @Builder
    public PersonalExercise(String title, LocalDateTime lessonDate, String timeSlot, List<Exercise> exercises, ClassType classType) {
        this.title = title;
        this.lessonDate = lessonDate;
        this.timeSlot = timeSlot;
        this.exercises = exercises;
        this.completionToggle = false;
        this.classType = classType;
    }

    public void updatePersonalExercise(Update updateRequest) {
        this.title = updateRequest.getTitle();
        this.lessonDate = updateRequest.getLessonDate();
        this.timeSlot = updateRequest.getTimeSlot();
        this.completionToggle = updateRequest.isCompletionToggle();
        this.classType = ClassType.findType(updateRequest.getClassType());
    }

    public void addExerciseDiary(ExerciseDiary exerciseDiary) {
        this.exerciseDiary = exerciseDiary;
    }

    public void addExercise(final List<Exercise> exercises) {
        if (this.exercises == null) {
            this.exercises = new ArrayList<>();
        }
        this.exercises.addAll(exercises);
    }

    public void updateExerciseDiary(final ExerciseDiary exerciseDiary) {
        this.exerciseDiary = exerciseDiary;
    }

    public void updateExercise(final Exercise updatedExercise) {
        exercises.replaceAll(existingExercise -> {
            if (existingExercise.getId().equals(updatedExercise.getId())) {
                return updatedExercise;
            }
            return existingExercise;
        });
    }
}
