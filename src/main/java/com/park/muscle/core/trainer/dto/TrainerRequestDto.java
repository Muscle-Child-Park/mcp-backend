package com.park.muscle.core.trainer.dto;

import com.park.muscle.core.member.domain.Role;
import com.park.muscle.core.trainer.domain.DayOff;
import com.park.muscle.core.trainer.domain.Gym;
import com.park.muscle.core.trainer.domain.Name;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.global.enumerate.SocialType;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

public class TrainerRequestDto {

    @Getter
    public static class LoginRequest {

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
    public static class GymRequest {
        @NotBlank(message = "name은 반드시 존재해야 합니다.")
        private String name;

        public Gym toEntity(String name) {
            return Gym.builder()
                    .name(name)
                    .build();
        }
    }

    @Getter
    public static class DayOffRequest {
        @NotBlank(message = "startDate는 반드시 존재해야 합니다.")
        private LocalDateTime startDate;

        @NotBlank(message = "endDate는 반드시 존재해야 합니다.")
        private LocalDateTime endDate;

        @NotBlank(message = "cycleStatus는 반드시 존재해야 합니다.")
        private boolean cycleStatus;

        public DayOff toEntity() {
            return DayOff.builder()
                    .startDate(startDate)
                    .endDate(endDate)
                    .cycleStatus(cycleStatus)
                    .build();
        }
    }
}
