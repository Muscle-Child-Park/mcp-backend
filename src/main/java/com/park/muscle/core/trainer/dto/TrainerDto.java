package com.park.muscle.core.trainer.dto;

import com.park.muscle.core.member.domain.Role;
import com.park.muscle.core.trainer.domain.Name;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.global.enumerate.SocialType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TrainerDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest {

        public Trainer toEntity;

        @NotBlank(message = "소셜 UID는 반드시 존재해야 합니다.")
        private String socialId;

        @NotBlank(message = "소셜 타입은 반드시 존재해야 합니다.")
        private String socialType;

        @NotBlank(message = "이름은 반드시 존재해야 합니다.")
        private String name;

        public String createUserNumber() {
            return String.format("%s#%s", SocialType.KAKAO, this.getSocialId());
        }

        public Trainer toEntity() {
            return Trainer.builder()
                    .socialId(createUserNumber())
                    .socialType(SocialType.findType(this.socialType))
                    .name(Name.from(this.name))
                    .role(Role.ROLE_TRAINER)
                    .build();
        }
    }

    @Getter
    @Builder
    @ApiModel("Trainer-LoginResponseDTO")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResponse {

        @ApiModelProperty("발급된 액세스 토큰")
        private String accessToken;

        @ApiModelProperty("발급된 리프래쉬 토큰")
        private String refreshToken;

        @ApiModelProperty("트레이너의 id만 반환되는 dto")
        private SignUpRequest trainer;
    }

    @Getter
    @NoArgsConstructor
    @ApiModel("Trainer-SignUpRequestDTO")
    public static class SignUpRequest {
        @ApiModelProperty("trainer의 고유 ID")
        private String trainerId;

        public SignUpRequest(Trainer trainer) {
            this.trainerId = trainer.getId().toString();
        }
    }

    @Getter
    @Builder
    @ApiModel("Trainer-SingUpResponseDTO")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUpResponse {
        @ApiModelProperty("trainer의 고유 ID")
        private String trainerId;
    }
}
