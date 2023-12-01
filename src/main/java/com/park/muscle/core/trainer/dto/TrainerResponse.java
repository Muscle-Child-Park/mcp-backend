package com.park.muscle.core.trainer.dto;

import com.park.muscle.core.reservation.dto.ReservationResponse.ReservationInfoResponse;
import com.park.muscle.core.ticket.dto.response.TicketResponse.PendingMemberNameResponse;
import com.park.muscle.core.trainer.domain.Trainer;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class TrainerResponse {

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
    @Schema(name = "Trainer-SingUp Response DTO")
    public static class SignUpResponse {
        @Schema(description = "trainer 고유 아이디")
        private final long trainerId;
        @Schema(description = "trainer 고유 태그")
        private final String trainerTag;

        public SignUpResponse(Trainer trainer) {
            this.trainerId = trainer.getId();
            this.trainerTag = trainer.getUniqueTag().formattedId();
        }
    }

    @Getter
    @Builder
    @Schema(name = "Trainer-ticket info Response DTO")
    public static class TrainerTicketInfoResponse {
        private Long trainerId;
        private String name;
        private LocalDateTime ticketGenerateInfo;
        private int totalQuantity;
        private int leftQuantity;

        public static TrainerTicketInfoResponse fromEntity(Long trainerId, String name, LocalDateTime ticketGenerateInfo, int totalQuantity, int leftQuantity) {
            return TrainerTicketInfoResponse.builder()
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
    @Schema(name = "Trainer-Member To Trainer Response DTO")
    public static class MemberToTrainerResponse {
        @Schema(description = "Trainer's unique identifier")
        private Long trainerId;
        @Schema(description = "Trainer's name")
        private String name;
        @Schema(description = "Trainer's ticket info")
        private LocalDateTime ticketGenerateInfo;
        @Schema(description = "total ticket quantity")
        private int totalQuantity;
        @Schema(description = "left ticket quantity")
        private int leftQuantity;

        public static TrainerTicketInfoResponse fromEntity(Long trainerId, String name, LocalDateTime ticketGenerateInfo, int totalQuantity, int leftQuantity) {
            return TrainerTicketInfoResponse.builder()
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
    @Schema(name = "Trainer-Trainer Home Response DTO")
    public static class TrainerHomeResponse {

        @Schema(description = "List of pending member name responses")
        PendingMemberNameResponse pendingMemberNameResponse;
        @Schema(description = "List of reservation information responses")
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
