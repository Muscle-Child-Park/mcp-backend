package com.park.muscle.memberinfo.command.domain;

import java.util.Optional;
import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface MemberInfoRepository extends Repository<MemberInfo, MemberInfoId> {

    Optional<MemberInfo> findById(MemberInfoId memberInfoId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "javax.persistence.lock.timeout", value = "3000")
    })
    @Query("select m from Member m where m.memberInfo.id = :id")
    Optional<MemberInfo> findByIdForUpdate(@Param("id") MemberInfoId memberInfoId);

    void save(MemberInfo memberInfo);


}
