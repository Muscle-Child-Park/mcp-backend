package com.park.muscle.memberinfo.command.application;

import com.park.muscle.memberinfo.command.domain.MemberInfo;
import com.park.muscle.memberinfo.command.domain.MemberInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
//@RequiredArgsConstructor
public class MemberInfoService {

    private final MemberInfoRepository memberInfoRepository;

    public MemberInfoService(MemberInfoRepository memberInfoRepository) {
        this.memberInfoRepository = memberInfoRepository;
    }

    // TODO: 2023/07/09 회원정보 등록
    @Transactional(readOnly = false)
    public void createMemberInfo(MemberInfo memberInfo) {
        memberInfoRepository.save(memberInfo);
    }

    // TODO: 2023/07/09 회원정보 전체 조회
//    public List<MemberInfo> findMemberInfo(){
//        return memberInfoRepository.findAll();
//    }

    // TODO: 2023/07/09 회원정보 한 명 조회
//    public MemberInfo findOne(MemberInfo memberInfo){
//        return memberInfoRepository.findOne(memberInfo.getId());
//    }

}
