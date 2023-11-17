package com.park.muscle.core.reservation.application;

import com.park.muscle.core.reservation.domain.Reservation;
import com.park.muscle.core.reservation.domain.ReservationRepository;
import com.park.muscle.core.reservation.domain.ReserveTimeSlot;
import com.park.muscle.core.reservation.dto.ReservationRequest.Create;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public void registerReservation(final Create request) {
        Reservation reservation = request.toEntity();
        List<ReserveTimeSlot> reserveTimeSlots = reservation.getReserveTimeSlots();
        for (ReserveTimeSlot timeSlot : reserveTimeSlots){
            timeSlot.setReservation(reservation);
            timeSlot.updateAccess();
        }
        reservationRepository.save(reservation);
    }
}
