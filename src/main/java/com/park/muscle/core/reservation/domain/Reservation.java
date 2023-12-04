package com.park.muscle.core.reservation.domain;

import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.global.entity.BaseEntity;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name = "reservation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseEntity {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "reservation_date_time", nullable = false)
    private LocalDateTime reservationDateTime;

    @Column(name = "reservation_modified_time")
    private LocalDateTime reservationModifiedTime;

    @Column(name = "reservation_cancelled_time")
    private LocalDateTime reservationCancelledTime;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<ReserveTimeSlot> reserveTimeSlots = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket;

    @Builder
    public Reservation(LocalDateTime reservationDate, List<ReserveTimeSlot> reserveTimeSlots) {
        this.reserveTimeSlots = reserveTimeSlots;
        this.reservationDateTime = reservationDate;
    }

    public int getReserveYear() {
        return reservationDateTime.getYear();
    }

    public int getReserveMonth() {
        return reservationDateTime.getMonthValue();
    }

    public int getReserveDay() {
        return reservationDateTime.getDayOfMonth();
    }

    public boolean isReserveCurrentTimes() {
        return reservationDateTime.toLocalDate().isEqual(LocalDate.now());
    }

    public String getReserveDayOfWeek() {
        DayOfWeek dayOfWeek = reservationDateTime.getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    public LocalDateTime changeReserveTime(List<ReserveTimeSlot> newReserveTime) {
        this.reserveTimeSlots = newReserveTime;
        this.reservationModifiedTime = LocalDateTime.now();
        return reservationModifiedTime;
    }

    public LocalDateTime cancelReserve() {
        this.reserveTimeSlots.clear();
        this.reservationCancelledTime = LocalDateTime.now();
        return reservationCancelledTime;
    }

    public void updateTicket(final Ticket ticket) {
        this.ticket = ticket;
    }
}
