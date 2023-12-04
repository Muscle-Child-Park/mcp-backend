package com.park.muscle.core.reservation.dto;

import com.park.muscle.core.reservation.domain.Reservation;
import com.park.muscle.core.reservation.domain.ReserveTimeSlot;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;

public class ReservationRequest {

    @Getter
    public static class Create {
        @NotNull(message = "Reservation ticket-id is required")
        @Schema(description = "Reservation ticker ID")
        private Long ticketId;

        @NotNull(message = "Reservation trainer-id is required")
        @Schema(description = "Trainer's identifier ID")
        private Long trainerId;

        @NotNull(message = "Reservation date is required")
        @Schema(description = "Reservation date", example = "2023-11-17T08:00:00")
        private LocalDateTime reservationDate;

        @NotNull(message = "Reservation time slot is required")
        @Schema(description = "Reservation time slot", example = "08:00")
        private List<ReserveTimeSlot> timeSlot;

        public Reservation toEntity() {
            return Reservation.builder()
                    .reservationDate(reservationDate)
                    .reserveTimeSlots(timeSlot)
                    .build();
        }
    }
}
