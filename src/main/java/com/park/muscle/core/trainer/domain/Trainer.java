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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trainer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

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

    @OneToMany(mappedBy = "trainer")
    private final List<DayOff> dayOffs = new ArrayList<>();

    @OneToMany(mappedBy = "trainer")
    private final List<Ticket> tickets = new ArrayList<>();

    @OneToMany
    private final List<Reservation> reservations = new ArrayList<>();

    @OneToMany
    private final List<ReserveTimeSlot> reserveTimeSlots = new ArrayList<>();

    @Builder
    public Trainer(Gym gym, SocialType socialType, String socialId, Name name, Role role) {
        this.socialType = socialType;
        this.socialId = socialId;
        this.gym = gym;
        this.name = name;
        this.role = role;
        this.uniqueTag = UniqueTag.builder().trainer(this).build();
    }

    public void updateName(String name) {
        this.name = Name.from(name);
    }

    public String getName() {
        return name.getName();
    }

    public void changeGym(Gym gym) {
        this.gym = gym;
        gym.getTrainers().add(this);
    }

    public void addDayOffs(DayOff dayOff) {
        this.dayOffs.add(dayOff);
        dayOff.setTrainer(this);
    }
}
