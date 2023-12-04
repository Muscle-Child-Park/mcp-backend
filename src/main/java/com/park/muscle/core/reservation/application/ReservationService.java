package com.park.muscle.core.reservation.application;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.reservation.domain.Reservation;
import com.park.muscle.core.reservation.domain.ReservationRepository;
import com.park.muscle.core.reservation.domain.ReservationTimeSlotRepository;
import com.park.muscle.core.reservation.domain.ReserveTimeSlot;
import com.park.muscle.core.reservation.dto.ReservationRequest.Create;
import com.park.muscle.core.reservation.dto.ReservationResponse.ReservationInfoResponse;
import com.park.muscle.core.reservation.dto.ReservationResponse.ReserveTimeSlotResponse;
import com.park.muscle.core.ticket.application.TicketService;
import com.park.muscle.core.ticket.domain.Ticket;
import java.time.LocalTime;
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
    private final TicketService ticketService;
    private final ReservationRepository reservationRepository;
    private final ReservationTimeSlotRepository reservationTimeSlotRepository;

    @Transactional
    public void registerReservation(final Create request) {
        Ticket ticketById = ticketService.findTicketById(request.getTicketId());
        Reservation reservation = request.toEntity();
        List<ReserveTimeSlot> reserveTimeSlots = reservation.getReserveTimeSlots();
        for (ReserveTimeSlot timeSlot : reserveTimeSlots){
            timeSlot.setReservation(reservation);
            timeSlot.updateAccess();
        }
        reservation.updateTicket(ticketById);
        reservationTimeSlotRepository.saveAll(reserveTimeSlots);
        reservationRepository.save(reservation);
    }

    @Transactional
    public List<ReservationInfoResponse> findReservationsInfo(final List<Ticket> tickets) {
        return tickets.stream()
                .map(ticket -> {
                    Member member = ticket.getMember();
                    List<Reservation> reservations = ticket.getReservations();
                    ReserveTimeSlotResponse timeSlots = dayMatched(reservations);
                    return ReservationInfoResponse.fromEntity(member.getId(), member.getMemberName(),
                            timeSlots, ticket.hasLessonToTicket());
                })
                .collect(Collectors.toList());
    }

    private ReserveTimeSlotResponse dayMatched(final List<Reservation> reservations) {
        List<Long> reservationIds = extractReservationIds(reservations);
        List<ReserveTimeSlot> reservationTimeSlots = findReservationTimeSlots(reservationIds);

        List<LocalTime> matchedTimeSlots = reservationTimeSlots.stream()
                .filter(reserveTimeSlot -> reserveTimeSlot.getReservation().isReserveCurrentTimes())
                .map(ReserveTimeSlot::getTime)
                .collect(Collectors.toList());
        return ReserveTimeSlotResponse.fromEntity(matchedTimeSlots);
    }

    private List<Long> extractReservationIds(final List<Reservation> reservations) {
        return reservations.stream()
                .map(Reservation::getId)
                .collect(Collectors.toList());
    }

    private List<ReserveTimeSlot> findReservationTimeSlots(List<Long> reservationIds) {
        return reservationIds.stream()
                .flatMap(reservationId -> reservationTimeSlotRepository.findAllByReservationId(reservationId).stream())
                .collect(Collectors.toList());
    }
}
