package com.park.muscle.core.trainer.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    @Query("select t from Trainer t where t.socialId = :username ")
    Optional<Trainer> findByUsername(@Param("username") String username);
}
