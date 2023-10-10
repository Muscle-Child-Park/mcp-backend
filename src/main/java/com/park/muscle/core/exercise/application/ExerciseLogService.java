package com.park.muscle.core.exercise.application;

import com.park.muscle.core.exercise.dto.request.ExerciseLogReflectionRequest;
import com.park.muscle.core.exercise.dto.request.ExerciseLogRequest;
import com.park.muscle.core.exercise.dto.response.ExerciseLogResponse;
import org.springframework.stereotype.Service;

@Service
public class ExerciseLogService {
    public boolean addExerciseLog(final Long memberId, final ExerciseLogRequest logRequest) {
        return false;
    }

    public ExerciseLogResponse findPersonalExerciseLog(final Long memberId) {
        return null;
    }

    public boolean updateExerciseLog(final Long memberId, final Long logId, final ExerciseLogRequest logRequest) {
        return false;
    }

    public boolean addExerciseLogReflection(final Long memberId, final Long logId,
                                            final ExerciseLogReflectionRequest request) {
        return false;
    }
}
