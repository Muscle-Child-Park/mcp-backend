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
import lombok.Getter;

@Getter
@Entity
public class ReserveTimeSlot {

    @Id
    @Column(name = "rserve_time_slot_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalTime time;

    @Column
    private boolean accessible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public void updateAccess() {
        this.accessible = true;
    }

    public void setReservation(final Reservation reservation) {
        this.reservation = reservation;
    }
}
