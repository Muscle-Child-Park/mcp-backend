package com.park.muscle.core.exercise.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseLogRepository extends JpaRepository<ExerciseDiary, Long> {
}
