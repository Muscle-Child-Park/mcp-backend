package com.park.muscle.core.exercise.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.park.muscle.core.exercise.exception.ClassTypeNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ClassTypeTest {

    @DisplayName("수업 타입을 찾을 수 있다.")
    @Test
    void 수업_타입() {
        String type = "pt";
        ClassType classType = ClassType.findType(type);
        assertThat(classType).isEqualTo(ClassType.PT);
    }

    @DisplayName("수업 타입이 올바르지 않다면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"p","t","co-pt"})
    void 수업_타입_불일치(String invalidType) {
        assertThatThrownBy(() -> ClassType.findType(invalidType))
                .isInstanceOf(ClassTypeNotFoundException.class)
                .hasMessageContaining("올바른 수업 타입을 찾을 수 없습니다.");
    }
}