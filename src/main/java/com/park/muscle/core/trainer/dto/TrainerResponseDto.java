package com.park.muscle.core.trainer.dto;

import com.park.muscle.core.trainer.domain.Trainer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

public class TrainerResponseDto {

    @Getter
    @Builder
    @ApiModel("Trainer-LoginResponseDTO")
    public static class LoginResponse {

        @ApiModelProperty("발급된 액세스 토큰")
        private String accessToken;

        @ApiModelProperty("발급된 리프래쉬 토큰")
        private String refreshToken;

        @ApiModelProperty("트레이너의 id만 반환되는 dto")
        private SignUpResponse trainer;

        public static LoginResponse fromEntity(String accessToken, String refreshToken, SignUpResponse trainer) {
            return LoginResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .trainer(trainer)
                    .build();
        }
    }

    @Getter
    @ApiModel("Trainer-SignUpRequestDTO")
    public static class SignUpRequest {
        @ApiModelProperty("trainer의 고유 ID")
        private String trainerId;

        public SignUpRequest(Trainer trainer) {
            this.trainerId = trainer.getId().toString();
        }
    }

    @Getter
    @ApiModel("Trainer-SingUpResponseDTO")
    public static class SignUpResponse {
        @ApiModelProperty("trainer의 고유 태그")
        private final String trainerId;
        private final String trainerTag;

        public SignUpResponse(Trainer trainer) {
            this.trainerId = trainer.getId().toString();
            this.trainerTag = trainer.getUniqueTag().formattedId();
        }
    }

    @Getter
    @Builder
    @ApiModel("Trainer-findResponseDTO")
    public static class FindResponse {
        private Long trainerId;
        private String name;
        private LocalDateTime ticketGenerateInfo;
        private int totalQuantity;
        private int leftQuantity;

        public static TrainerResponseDto.FindResponse fromEntity(Long trainerId, String name, LocalDateTime ticketGenerateInfo, int totalQuantity, int leftQuantity) {
            return FindResponse.builder()
                    .trainerId(trainerId)
                    .name(name)
                    .ticketGenerateInfo(ticketGenerateInfo)
                    .totalQuantity(totalQuantity)
                    .leftQuantity(leftQuantity)
                    .build();
        }
    }


}
