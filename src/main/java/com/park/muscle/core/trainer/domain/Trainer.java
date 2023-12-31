package com.park.muscle.core.trainer.domain;

import com.park.muscle.core.member.domain.Role;
import com.park.muscle.core.reservation.domain.Reservation;
import com.park.muscle.core.reservation.domain.ReserveTimeSlot;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.uniquetag.domain.UniqueTag;
import com.park.muscle.global.entity.BaseEntity;
import com.park.muscle.global.entity.Users;
import com.park.muscle.global.enumerate.SocialType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "trainer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trainer extends BaseEntity implements Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "social_type")
    private SocialType socialType;

    @Column(name = "social_id", nullable = false)
    private String socialId;

    @Embedded
    private Name name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private UniqueTag uniqueTag;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "gym_id")
    private final List<Gym> gym = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<DayOff> dayOffs = new ArrayList<>();

    @OneToMany(mappedBy = "trainer")
    private final List<Ticket> tickets = new ArrayList<>();

    @OneToMany
    private final List<Reservation> reservations = new ArrayList<>();

    @OneToMany
    private final List<ReserveTimeSlot> reserveTimeSlots = new ArrayList<>();

    @Builder
    public Trainer(SocialType socialType, String socialId, Name name, Role role) {
        this.socialType = socialType;
        this.socialId = socialId;
        this.name = name;
        this.role = role;
        UniqueTag uniqueTag = UniqueTag.builder().build();
        uniqueTag.updateTrainer(this);
        this.uniqueTag = uniqueTag;
    }

    public Long getUniqueTagId() {
        return uniqueTag.getId();
    }

    public void updateName(String name) {
        this.name = Name.from(name);
    }

    public String getName() {
        return name.getName();
    }

    public void addGym(Gym gym) {
        this.gym.add(gym);
    }

    public void addDayOffs(DayOff dayOff) {
        this.dayOffs.add(dayOff);
    }

    public boolean isNew() {
        return id == null;
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }
}
