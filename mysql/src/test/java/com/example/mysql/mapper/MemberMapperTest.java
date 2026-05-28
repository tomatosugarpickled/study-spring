package com.example.mysql.mapper;

import com.example.mysql.common.enumeration.Status;
import com.example.mysql.domain.MemberVO;
import com.example.mysql.domain.PostVO;
import com.example.mysql.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class MemberMapperTest {
    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void testInsert(){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("hgd1234@naver.com");
        memberDTO.setMemberPassword("1234");
        memberDTO.setMemberName("홍길동");

        memberMapper.insert(memberDTO.toVO());
    }

    @Test
    public void testInsertInactive(){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("lss1212@hanmail.net");
        memberDTO.setMemberPassword("1212");
        memberDTO.setMemberName("이순신");
        memberDTO.setMemberStatus(Status.INACTIVE);

        memberMapper.insertInactive(memberDTO.toVO());
    }

    @Test
    public void testSelectById(){
        Optional<MemberVO> foundMember = memberMapper.selectById(1L);
        log.info("{}", foundMember.orElse(null));
    }

    @Test
    public void testSelectAll(){
        List<MemberVO> memberVOList = memberMapper.selectAll();
        memberVOList.stream().map(MemberVO::toString).forEach(log::info);
    }


    @Test
    public void testUpdateStatus(){
        Optional<MemberVO> foundMember = memberMapper.selectById(1L);
        foundMember.ifPresent((memberVO) ->  {
            if(memberVO.getMemberStatus().getValue().equals(Status.ACTIVE.getValue())){
                memberMapper.updateStatus(memberVO.getId());
            }
        });
    }

    @Test
    public void testDelete(){
        Optional<MemberVO> foundMember = memberMapper.selectById(1L);
        foundMember.ifPresent((memberVO) ->  {
            memberMapper.delete(memberVO.getId());
        });
    }

}


















