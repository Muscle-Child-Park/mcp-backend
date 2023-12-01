package com.park.muscle.core.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReservationResponse {

    @Getter
    @Builder
    @Schema(name = "Reservation Info Response DTO")
    public static class ReservationInfoResponse {
        @Schema(description = "Member's identifier ID")
        private Long memberId;
        @Schema(description = "Trainer name")
        private String trainerName;
        @Schema(description = "Member name")
        private String memberName;
        @Schema(description = "Reservation time slot list", example = "")
        private ReserveTimeSlotResponse reserveTimeSlotResponse;

        private boolean hasLessonSchedule;

        public static ReservationInfoResponse fromEntity(Long memberId, String memberName,
                                                         ReserveTimeSlotResponse reserveTimeSlotResponse,
                                                         boolean hasLessonSchedule) {
            return ReservationInfoResponse.builder()
                    .memberId(memberId)
                    .memberName(memberName)
                    .reserveTimeSlotResponse(reserveTimeSlotResponse)
                    .hasLessonSchedule(hasLessonSchedule)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class ReserveTimeSlotResponse {
        @Schema(description = "Reservation time slot list", example = "")
        private List<LocalTime> reserveTimeSlots;
        @Schema(description = "Reservation accessibleTime list", example = "")
        private List<Boolean> accessibleTime;

        public static ReserveTimeSlotResponse fromEntity(List<LocalTime> reserveTimeSlots){
            return ReserveTimeSlotResponse.builder()
                    .reserveTimeSlots(reserveTimeSlots)
                    .build();
        }
    }
}
