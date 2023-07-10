package com.park.muscle.member.command.domain;

import java.util.Optional;
import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends Repository<Member, MemberId> {

    // TODO 동일한 기능의 쿼리 메서드가 왜 2개가 있을까요?! 비관적 락을 걸으셨는데, 트랜잭션에서 부터 락을 걸게되면 수많은 요청이 있을때 오버헤드가 발생할 수 있지 않을까요?!
    Optional<Member> findById(MemberId memberId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "javax.persistence.lock.timeout", value = "3000")
    })
    @Query("select m from Member m where m.id = :id")
    Optional<Member> findByIdForUpdate(@Param("id") MemberId memberId);

    void save(Member member);
}
