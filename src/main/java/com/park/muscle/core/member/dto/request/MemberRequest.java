package com.park.muscle.core.member.dto.request;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.member.domain.Name;
import com.park.muscle.core.member.domain.Role;
import com.park.muscle.core.onboarding.domain.Onboarding;
import com.park.muscle.global.enumerate.SocialType;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

public class MemberRequest {

    @Getter
    @Tag(name = "로그인 요청 DTO", description = "온보딩 작성전, 입력 받은 소셜 정보가 필요합니다.")
    public static class LoginRequest {

        @NotBlank(message = "소셜 UID는 반드시 존재해야 합니다.")
        @Schema(description = "소셜 로그인 아이디")
        private String socialId;

        @NotBlank(message = "소셜 타입은 반드시 존재해야 합니다.")
        @Schema(description = "소셜 로그인 플랫폼", example = "KAKAO, GOOGLE, APPLE")
        private String socialType;

        @NotBlank(message = "이름은 반드시 존재해야 합니다.")
        @Schema(description = "유저 이름")
        private String name;

        public String createUserNumber() {
            return String.format("%s#%s", SocialType.KAKAO, this.getSocialId());
        }

        private LoginRequest() {
        }

        public Member toEntity() {
            return Member.builder()
                    .socialId(createUserNumber())
                    .socialType(SocialType.findType(this.socialType))
                    .name(Name.from(this.name))
                    .role(Role.ROLE_MEMBER)
                    .build();
        }
    }

    @Getter
    @Tag(name = "유저 온보딩 요청 DTO")
    public static class OnboardingQuestionRequest {

        @NotBlank(message = "첫 번째 운동 목적은 반드시 입력해야 합니다.")
        @Schema(description = "신체 목표")
        private String bodyPurpose;

        @NotBlank(message = "두 번째 운동 목적은 반드시 입력해야 합니다.")
        @Schema(description = "운동 목적")
        private String exercisePurpose;

        @NotBlank(message = "식단과 운동 방식 입력은 반드시 입력해야 합니다.")
        @Schema(description = "식단, 운동 밸런스")
        private String balance;

        @NotBlank(message = "운동 목적은 반드시 입력해야 합니다.")
        @Schema(description = "운동 관심도")
        private String interest;

        @NotBlank(message = "생활 패턴은 반드시 입력해야 합니다.")
        @Schema(description = "평소 생활 습관")
        private String lifeStyle;

        @NotBlank(message = "이름은 반드시 입력해야 합니다.")
        @Schema(description = "회원 이름")
        private String name;

        @Schema(deprecated = true)
        private String eatingHabit;

        public Onboarding toEntity() {
            return Onboarding.builder()
                    .bodyPurpose(bodyPurpose)
                    .exercisePurpose(exercisePurpose)
                    .balance(balance)
                    .interest(interest)
                    .lifeStyle(lifeStyle)
                    .eatingHabit(eatingHabit)
                    .name(name)
                    .build();
        }
    }
}
