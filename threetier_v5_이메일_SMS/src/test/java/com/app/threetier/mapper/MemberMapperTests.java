package com.app.threetier.mapper;

import com.app.threetier.common.enumeration.Provider;
import com.app.threetier.domain.MemberVO;
import com.app.threetier.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class MemberMapperTests {
    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void testSelectByMemberEmail(){
        Optional<MemberDTO> foundMember = memberMapper.selectByMemberEmail("fdsfds@gmail.com");
        log.info("{}.....", foundMember.isEmpty());
    }

    @Test
    public void testInsert(){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("test@gmail.com");
        memberDTO.setMemberPassword("1234");
        memberDTO.setMemberName("test");
        memberDTO.setProvider(Provider.THREETIER);

        memberMapper.insert(memberDTO);
        memberMapper.insertOauth(memberDTO.toOAuthVO());
    }

    @Test
    public void testSelectMemberForLogin(){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("test32131321@gmail.com");
        memberDTO.setMemberPassword("1234");

        Optional<MemberDTO> foundMember = memberMapper.selectMemberForLogin(memberDTO);
        log.info("{}...........", foundMember.isEmpty());
    }

}










