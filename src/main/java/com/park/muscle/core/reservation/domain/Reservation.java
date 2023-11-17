package com.park.muscle.core.reservation.domain;

import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.global.entity.BaseEntity;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseEntity {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private boolean confirm;

    @Column(nullable = false)
    private LocalDateTime reservationDateTime;

    @Column
    private LocalDateTime reservationModifiedTime;

    @Column
    private LocalDateTime reservationCancelledTime;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<ReserveTimeSlot> reserveTimeSlots = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public int getReserveYear() {
        return reservationDateTime.getYear();
    }

    public int getReserveMonth() {
        return reservationDateTime.getMonthValue();
    }

    public int getReserveDay() {
        return reservationDateTime.getDayOfMonth();
    }

    public String getReserveDayOfWeek() {
        DayOfWeek dayOfWeek = reservationDateTime.getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    public void reserveConfirm() {
        this.confirm = true;
    }

    public LocalDateTime changeReserveTime(List<ReserveTimeSlot> newReserveTime) {
        this.reserveTimeSlots = newReserveTime;
        this.reservationModifiedTime = LocalDateTime.now();
        return reservationModifiedTime;
    }

    public LocalDateTime cancelReserve() {
        this.reserveTimeSlots.clear();
        this.reservationCancelledTime = LocalDateTime.now();
        this.confirm = false;
        return reservationCancelledTime;
    }

    @Builder
    public Reservation(List<ReserveTimeSlot> reserveTimeSlots, LocalDateTime reservationTime) {
        this.reserveTimeSlots = reserveTimeSlots;
        this.reservationDateTime = reservationTime;
    }
}
