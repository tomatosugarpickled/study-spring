package com.app.app.repository;

import com.app.app.common.enumeration.OAuthProvider;
import com.app.app.domain.OAuthVO;
import com.app.app.dto.MemberDTO;
import com.app.app.mapper.OAuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OAuthDAO {
    private final OAuthMapper oAuthMapper;

//    회원가입
    public void save(OAuthVO oAuthVO){
        oAuthMapper.insert(oAuthVO);
    }
//    로그인
    public Optional<MemberDTO> findMemberForLogin(MemberDTO memberDTO){
        return oAuthMapper.selectMemberForLogin(memberDTO);
    }
//    이메일로 조회
    public Optional<MemberDTO> findMemberByMemberEmail(String memberEmail, OAuthProvider provider){
        return oAuthMapper.selectMemberByMemberEmail(memberEmail, provider);
    }
}
