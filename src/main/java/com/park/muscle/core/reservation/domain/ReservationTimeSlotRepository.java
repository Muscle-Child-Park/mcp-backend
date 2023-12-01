package com.park.muscle.core.reservation.domain;

import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationTimeSlotRepository extends JpaRepository<ReserveTimeSlot, Long> {
    ReserveTimeSlot findByTime(LocalTime time);
    ReserveTimeSlot findByReservationId(Long reservationId);
    List<ReserveTimeSlot> findAllByReservationId(Long reservationId);
}
