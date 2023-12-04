package com.park.muscle.core.member.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.park.muscle.core.member.exception.RoleNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RoleTest {

    @DisplayName("역할을 찾을 수 있다.")
    @Test
    void 일치하는_역할_테스트() {
        String role = "member";
        Role actualRole = Role.getRole(role);
        assertThat(actualRole.getAuthority()).isEqualTo("member");
    }

    @DisplayName("ROLE가 올바르지 않다면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"membe", "traine", "user"})
    void 일치하지않는_역할_테스트(String invalidRole) {
        assertThatThrownBy(() -> Role.getRole(invalidRole))
                .isInstanceOf(RoleNotFoundException.class)
                .hasMessageContaining("올바른 ROLE을 찾을 수 없습니다.");
    }
}