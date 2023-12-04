package com.park.muscle.core.member.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.park.muscle.core.member.exception.SocialTypeNotFoundException;
import com.park.muscle.global.enumerate.SocialType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SocialTypeTest {

    @DisplayName("소셜 타입을 찾을 수 있다.")
    @Test
    void findType() {
        String typeValue = "kakao";
        SocialType findType = SocialType.findType(typeValue);
        assertThat(findType).isEqualTo(SocialType.KAKAO);
    }

    @DisplayName("소셜 타입이 올바르지 않다면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"kkao", "gogle", "aple"})
    void findType_fail_invalidParam(String invalidTypeValue) {
        assertThatThrownBy(() -> SocialType.findType(invalidTypeValue))
                .isInstanceOf(SocialTypeNotFoundException.class)
                .hasMessageContaining("올바른 소셜 타입을 찾을 수 없습니다.");
    }

}