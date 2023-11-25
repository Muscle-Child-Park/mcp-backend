package com.park.muscle.core.trainer.domain;

import com.park.muscle.core.member.domain.Role;
import com.park.muscle.core.reservation.domain.Reservation;
import com.park.muscle.core.reservation.domain.ReserveTimeSlot;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.uniquetag.domain.UniqueTag;
import com.park.muscle.global.entity.BaseEntity;
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
public class Trainer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "gym_id")
    private final List<Gym> gym = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    @Column(nullable = false)
    private String socialId;

    @Embedded
    private Name name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    private UniqueTag uniqueTag;

    @OneToMany
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
        this.uniqueTag = new UniqueTag();
        uniqueTag.updateTrainer(this);
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
}
