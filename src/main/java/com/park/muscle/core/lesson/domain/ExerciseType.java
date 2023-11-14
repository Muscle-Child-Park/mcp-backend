package com.park.muscle.core.lesson.domain;

import com.park.muscle.core.lesson.exception.ExerciseTypeNotFoundException;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExerciseType {
    AEROBIC,
    ANAEROBIC;

    public static ExerciseType findType(String exerciseType) {
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(exerciseType))
                .findAny()
                .orElseThrow(ExerciseTypeNotFoundException::new);
    }
}
