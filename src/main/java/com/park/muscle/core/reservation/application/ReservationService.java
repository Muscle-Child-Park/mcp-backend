package com.park.muscle.core.reservation.application;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.reservation.domain.Reservation;
import com.park.muscle.core.reservation.domain.ReservationRepository;
import com.park.muscle.core.reservation.domain.ReservationTimeSlotRepository;
import com.park.muscle.core.reservation.domain.ReserveTimeSlot;
import com.park.muscle.core.reservation.dto.ReservationRequest.Create;
import com.park.muscle.core.reservation.dto.ReservationResponse.ReservationInfoResponse;
import com.park.muscle.core.reservation.dto.ReservationResponse.ReserveTimeSlotResponse;
import com.park.muscle.core.ticket.domain.Ticket;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationTimeSlotRepository reservationTimeSlotRepository;

    @Transactional
    public void registerReservation(final Create request) {
        Reservation reservation = request.toEntity();
        List<ReserveTimeSlot> reserveTimeSlots = reservation.getReserveTimeSlots();
        for (ReserveTimeSlot timeSlot : reserveTimeSlots){
            timeSlot.setReservation(reservation);
            timeSlot.updateAccess();
        }
        reservationTimeSlotRepository.saveAll(reserveTimeSlots);
        reservationRepository.save(reservation);
    }

    public List<ReservationInfoResponse> findReservationsInfo(final List<Ticket> tickets) {
        return tickets.stream()
                .map(ticket -> {
                    Member member = ticket.getMember();
                    Long memberId = member.getId();
                    String name = member.getName().getValue();
                    ReserveTimeSlotResponse timeSlots = dayMatched(member.getReservations());
                    boolean hasId = ticket.getLesson() != null;
                    return ReservationInfoResponse.fromEntity(memberId, name, timeSlots, hasId);
                })
                .collect(Collectors.toList());
    }

    private ReserveTimeSlotResponse dayMatched(final List<Reservation> reservations) {
        List<LocalTime> matchedTimeSlots = reservations.stream()
                .filter(reservation -> LocalDateTime.now().getDayOfMonth() == reservation.getReserveDay())
                .findFirst()
                .map(Reservation::getReserveTimeSlots)
                .orElse(Collections.emptyList())
                .stream()
                .map(ReserveTimeSlot::getTime)
                .collect(Collectors.toList());
        return ReserveTimeSlotResponse.fromEntity(matchedTimeSlots);
    }
}
