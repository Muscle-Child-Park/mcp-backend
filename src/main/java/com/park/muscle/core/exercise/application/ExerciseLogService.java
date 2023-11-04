package com.park.muscle.core.exercise.application;

import com.park.muscle.core.exercise.dto.request.ExerciseLogReflectionRequest;
import com.park.muscle.core.exercise.dto.request.ExerciseLogRequest;
import com.park.muscle.core.exercise.dto.response.ExerciseLogResponse;
import org.springframework.stereotype.Service;

@Service
public class ExerciseLogService {

    public boolean addExerciseLog(Long memberId, ExerciseLogRequest logRequest) {
        return false;
    }

    public ExerciseLogResponse findPersonalExerciseLog(Long memberId) {
        return null;
    }

    public boolean updateExerciseLog(Long memberId, Long logId, ExerciseLogRequest logRequest) {
        return false;
    }

    public boolean addExerciseLogReflection(Long memberId, Long logId,
                                            ExerciseLogReflectionRequest request) {
        return false;
    }
}
