package com.park.muscle.core.onboarding.domain;

import com.park.muscle.core.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnboardingRepository extends JpaRepository<Onboarding, Long> {
    Optional<Member> findByMemberId(Long memberId);
}
