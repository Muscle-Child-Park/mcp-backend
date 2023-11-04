package com.park.muscle.core.onboarding.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnboardingRepository extends JpaRepository<Onboarding, Long> {
    Optional<Onboarding> findByMemberId(Long memberId);
}
