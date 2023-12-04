package com.park.muscle.core.member.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.park.muscle.global.enumerate.SocialType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

    private Member member;

    @BeforeEach
    void setUp() {
        SocialType socialType = SocialType.KAKAO;
        String socialId = "123456778";
        Name name = Name.from("John");
        Role role = Role.ROLE_MEMBER;

        member = Member.builder()
                .socialType(socialType)
                .socialId(socialId)
                .name(name)
                .role(role)
                .build();
    }

    @DisplayName("멤버 소셜 타입 테스트")
    @Test
    void 멤버_소셜_타입() {
        assertThat(member.getSocialType().name()).isEqualTo("KAKAO");
    }

    @DisplayName("멤버 소셜 아이디 테스트")
    @Test
    void 멤버_소셜_아이디() {
        assertThat(member.getSocialId()).isEqualTo("123456778");
    }

    @DisplayName("멤버 이름 테스트")
    @Test
    void 멤버_이름() {
        assertThat(member.getMemberName()).isEqualTo("John");
    }

    @DisplayName("멤버 ROLE 테스트")
    @Test
    void 멤버_role() {
        assertThat(member.getRole().getAuthority()).isEqualTo("member");
    }

    @DisplayName("멤버 이름 업데이트 테스트")
    @Test
    void 멤버_이름_업데이트() {
        member.updateName("모나");
        assertThat(member.getMemberName()).isEqualTo("모나");
    }
}