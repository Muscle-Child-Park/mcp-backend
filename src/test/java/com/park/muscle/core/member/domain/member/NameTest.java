package com.park.muscle.core.member.domain.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.park.muscle.core.member.domain.Name;
import com.park.muscle.core.member.exception.InvalidNameLengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @DisplayName("멤버 이름을 생성할 수 있다.")
    @Test
    void 이름_추가() {
        Name name = Name.from("홍길동");
        assertThat(name.getValue()).isEqualTo("홍길동");
    }

    @DisplayName("멤버 이름이 10보다 크다면, 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"01234567890", "abcdefghijk"})
    void 잘못된_이름_길이(String invalidValue) {
        assertThatThrownBy(() -> Name.from(invalidValue))
                .isInstanceOf(InvalidNameLengthException.class)
                .hasMessageContaining("이름의 길이는 10자를 넘길 수 없습니다.");
    }
}