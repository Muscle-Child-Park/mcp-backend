package com.park.muscle.core.reservation.domain;

import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class ReserveTimeSlot {

    @Id
    @Column(name = "reserve_time_slot_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "accessible_time")
    private boolean accessibleTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public void updateAccess() {
        this.accessibleTime = true;
    }

    public void setReservation(final Reservation reservation) {
        this.reservation = reservation;
    }

    @Builder
    public ReserveTimeSlot(LocalTime time, boolean accessibleTime) {
        this.time = time;
        this.accessibleTime = accessibleTime;
    }
}
