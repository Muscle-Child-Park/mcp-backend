package com.park.muscle.core.trainer.domain;

import com.park.muscle.global.entity.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "day_off")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DayOff extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_off_id")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private boolean cycleStatus;

    @Builder
    public DayOff(LocalDateTime startDate, LocalDateTime endDate, boolean cycleStatus) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.cycleStatus = cycleStatus;
    }
}
