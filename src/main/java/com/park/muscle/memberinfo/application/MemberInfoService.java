package com.park.muscle.memberinfo.application;

import com.park.muscle.memberinfo.domain.MemberInfo;
import com.park.muscle.memberinfo.domain.MemberInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberInfoService {

    private final MemberInfoRepository memberInfoRepository;

    public MemberInfoService(MemberInfoRepository memberInfoRepository) {
        this.memberInfoRepository = memberInfoRepository;
    }

    @Transactional
    public void createMemberInfo(MemberInfo memberInfo) {
        memberInfoRepository.save(memberInfo);
    }
}