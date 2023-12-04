package com.park.muscle.core.exercise.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.park.muscle.core.exercise.exception.ExerciseTypeNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ExerciseTypeTest {

    @DisplayName("운동 타입을 찾을 수 있다.")
    @Test
    void 수업_타입() {
        String type = "AEROBIC";
        ExerciseType exerciseType = ExerciseType.findType(type);
        assertThat(exerciseType).isEqualTo(ExerciseType.AEROBIC);
    }

    @DisplayName("운동 타입이 올바르지 않다면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"A", "ERO", "BI", "AEROBI"})
    void 수업_타입_불일치(String invalidType) {
        assertThatThrownBy(() -> ExerciseType.findType(invalidType))
                .isInstanceOf(ExerciseTypeNotFoundException.class)
                .hasMessageContaining("올바른 운동 타입을 찾을 수 없습니다.");
    }

}