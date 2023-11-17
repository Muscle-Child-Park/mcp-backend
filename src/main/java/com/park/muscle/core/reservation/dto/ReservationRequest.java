package com.park.muscle.core.reservation.dto;

import com.park.muscle.core.reservation.domain.Reservation;
import com.park.muscle.core.reservation.domain.ReserveTimeSlot;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

public class ReservationRequest {

    @Slf4j
    @Getter
    public static class Create {

        @NotNull(message = "Reservation trainer-id is required")
        private Long trainerId;

        @NotNull(message = "Reservation date is required")
        private LocalDateTime reservationDate;

        @NotNull(message = "Reservation time slot is required")
        private List<ReserveTimeSlot> timeSlot;

        public Reservation toEntity() {
            return Reservation.builder()
                    .reservationDate(reservationDate)
                    .reserveTimeSlots(timeSlot)
                    .build();
        }
    }
}
