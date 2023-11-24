package com.park.muscle.core.exercise.domain;

import com.park.muscle.core.exercise.exception.ExerciseTypeNotFoundException;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClassType {

    PT,
    CO_PT;

    public static ClassType findType(String classType) {
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(classType))
                .findAny()
                .orElseThrow(ExerciseTypeNotFoundException::new);
    }
}
