package com.park.muscle.core.reservation.application;

import com.park.muscle.core.reservation.domain.ReservationTimeSlotRepository;
import com.park.muscle.core.reservation.domain.TimeTable;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationTimeSlotService {
    private final ReservationTimeSlotRepository reservationTimeSlotRepository;

    public void updateTimeSlotAccessibility(LocalTime time) {
    }

    public boolean isTimeSlotAccessible(LocalTime time) {
        return false;
    }

    private boolean getDefaultAccessibility(LocalTime time) {
        return TimeTable.valueOf("AM_" + time.getHour()).isAccessible();
    }
}
