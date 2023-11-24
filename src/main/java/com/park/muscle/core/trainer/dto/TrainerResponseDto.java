package com.park.muscle.core.trainer.dto;

import com.park.muscle.core.reservation.dto.ReservationResponse.ReservationInfoResponse;
import com.park.muscle.core.ticket.dto.TicketDto.PendingMemberNameResponse;
import com.park.muscle.core.trainer.domain.Trainer;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class TrainerResponseDto {

    @Getter
    @Builder
    @Schema(name = "Trainer-LoginResponseDTO")
    public static class LoginResponse {

        @Schema(description = "발급된 액세스 토큰")
        private String accessToken;

        @Schema(description = "발급된 리프래쉬 토큰")
        private String refreshToken;

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
    @Schema(name = "Trainer-SignUpRequestDTO")
    public static class SignUpRequest {
        @Schema(description = "trainer 고유 ID")
        private final String trainerId;

        public SignUpRequest(Trainer trainer) {
            this.trainerId = trainer.getId().toString();
        }
    }

    @Getter
    @Schema(name = "Trainer-SingUpResponseDTO")
    public static class SignUpResponse {
        @Schema(description = "trainer 고유 태그")
        private final String trainerId;
        private final String trainerTag;

        public SignUpResponse(Trainer trainer) {
            this.trainerId = trainer.getId().toString();
            this.trainerTag = trainer.getUniqueTag().formattedId();
        }
    }

    @Getter
    @Builder
    @Schema(name = "Trainer-findResponseDTO")
    public static class TrainerResponse {
        private Long trainerId;
        private String name;
        private LocalDateTime ticketGenerateInfo;
        private int totalQuantity;
        private int leftQuantity;

        public static TrainerResponse fromEntity(Long trainerId, String name, LocalDateTime ticketGenerateInfo, int totalQuantity, int leftQuantity) {
            return TrainerResponse.builder()
                    .trainerId(trainerId)
                    .name(name)
                    .ticketGenerateInfo(ticketGenerateInfo)
                    .totalQuantity(totalQuantity)
                    .leftQuantity(leftQuantity)
                    .build();
        }
    }

    @Getter
    @Builder
    @Schema(name = "Trainer-findResponseDTO")
    public static class MemberToTrainerResponse {
        private Long trainerId;
        private String name;
        private LocalDateTime ticketGenerateInfo;
        private int totalQuantity;
        private int leftQuantity;

        public static TrainerResponse fromEntity(Long trainerId, String name, LocalDateTime ticketGenerateInfo, int totalQuantity, int leftQuantity) {
            return TrainerResponse.builder()
                    .trainerId(trainerId)
                    .name(name)
                    .ticketGenerateInfo(ticketGenerateInfo)
                    .totalQuantity(totalQuantity)
                    .leftQuantity(leftQuantity)
                    .build();
        }
    }

    @Getter
    @Builder
    @Schema(name = "Trainer-homeResponse")
    public static class TrainerHomeResponse {
        PendingMemberNameResponse pendingMemberNameResponse;
        List<ReservationInfoResponse> reservationInfoResponses;

        public static TrainerHomeResponse fromEntity(PendingMemberNameResponse pendingMemberNameResponse,
                                                     List<ReservationInfoResponse> reservationInfoResponses) {
            return TrainerHomeResponse.builder()
                    .pendingMemberNameResponse(pendingMemberNameResponse)
                    .reservationInfoResponses(reservationInfoResponses)
                    .build();
        }
    }
}
