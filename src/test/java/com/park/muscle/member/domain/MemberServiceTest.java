package com.park.muscle.member.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.park.muscle.member.command.application.MemberService;
import com.park.muscle.member.command.domain.Member;
import com.park.muscle.member.command.domain.MemberRepository;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

//    @Test
//    public void 회원가입() throws Exception {
//        // given
//        Member member = new Member();
//        member.setName("kim");
//
//        // when
//        Long saveId = memberService.join(member);
//
//        // then
//        em.flush();
//        assertEquals(member, memberRepository.findOne(saveId));
//    }



}
