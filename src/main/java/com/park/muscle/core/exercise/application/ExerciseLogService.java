package com.park.muscle.core.exercise.application;

import com.park.muscle.core.exercise.dto.LogReflectionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExerciseLogService {
    public boolean addExerciseLogReflection(final Long memberId, final Long logId, final LogReflectionDto request) {
        return false;
    }

    public boolean addExerciseLog(final Long memberId, final LogReflectionDto logRequest) {
        return false;
    }

    public boolean updateExerciseLog(final Long memberId, final Long logId, final LogReflectionDto logRequest) {
        return false;
    }
}
