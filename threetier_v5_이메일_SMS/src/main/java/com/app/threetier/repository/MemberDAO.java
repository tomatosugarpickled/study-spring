package com.app.threetier.repository;

import com.app.threetier.domain.MemberVO;
import com.app.threetier.domain.OAuthVO;
import com.app.threetier.dto.MemberDTO;
import com.app.threetier.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;
//    이메일 검사
    public Optional<MemberDTO> findByMemberEmail(String memberEmail){
        return memberMapper.selectByMemberEmail(memberEmail);
    }

//    회원가입
    public void save(MemberDTO memberDTO){
        memberMapper.insert(memberDTO);
    }
//    oauth
    public void saveOAuth(OAuthVO oAuthVO){
        memberMapper.insertOauth(oAuthVO);
    }
//    로그인
    public Optional<MemberDTO> findForLogin(MemberDTO memberDTO){
        return memberMapper.selectMemberForLogin(memberDTO);
    }

}












