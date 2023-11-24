package com.park.muscle.core.reservation.dto;

import com.park.muscle.core.reservation.domain.ReserveTimeSlot;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReservationResponse {

    @Getter
    @Builder
    @Schema(name = "Member-findResponseDTO")
    public static class ReservationInfoResponse {
        private Long memberId;
        private String trainerName;
        private String memberName;
        private List<ReserveTimeSlot> reserveTimeSlots;
        private boolean hasLessonSchedule;

        public static ReservationInfoResponse fromEntity(Long memberId, String memberName, List<ReserveTimeSlot> reserveTimeSlots, boolean hasLessonSchedule) {
            return ReservationInfoResponse.builder()
                    .memberId(memberId)
                    .memberName(memberName)
                    .reserveTimeSlots(reserveTimeSlots)
                    .hasLessonSchedule(hasLessonSchedule)
                    .build();
        }
    }
}
