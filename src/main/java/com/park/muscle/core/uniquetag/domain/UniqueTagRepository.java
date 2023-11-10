package com.park.muscle.core.uniqueid.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UniqueIdRepository extends JpaRepository<UniqueTag, Long> {
}
