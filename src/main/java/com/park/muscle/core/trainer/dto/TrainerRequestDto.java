package com.park.muscle.core.trainer.dto;

import com.park.muscle.core.member.domain.Role;
import com.park.muscle.core.trainer.domain.Name;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.global.enumerate.SocialType;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

public class TrainerRequestDto {

    @Getter
    public static class LoginRequest {

        private Trainer toEntity;

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
}
