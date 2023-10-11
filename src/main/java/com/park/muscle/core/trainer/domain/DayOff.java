package com.park.muscle.core.trainer.domain;

import com.park.muscle.global.entity.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;

@Getter
@Entity
public class DayOff extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "day_off_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "trainer_id")
  private Trainer trainer;

  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private String timeSlot;
  private String cycleStatus;
}
