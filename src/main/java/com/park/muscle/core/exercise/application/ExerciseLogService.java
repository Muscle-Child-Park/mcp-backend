package com.park.muscle.core.exercise.application;

import com.park.muscle.core.exercise.domain.ExerciseDiary;
import com.park.muscle.core.exercise.domain.ExerciseLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExerciseLogService {
    private final ExerciseLogRepository exerciseLogRepository;

    public boolean addExerciseLogReflection(final Long memberId, final Long logId) {
        return false;
    }

    public boolean addExerciseLog(final Long memberId, final String log) {
        return false;
    }

    public boolean updateExerciseLog(final Long memberId, final Long logId) {
        return false;
    }

    public void save(final ExerciseDiary exerciseDiary) {
        exerciseLogRepository.save(exerciseDiary);
    }
}
