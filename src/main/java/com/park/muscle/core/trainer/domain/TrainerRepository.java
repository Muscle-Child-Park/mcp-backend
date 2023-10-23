package com.park.muscle.core.trainer.domain;

import com.park.muscle.core.trainer.domain.custom.TrainerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long>, TrainerRepositoryCustom {
}
