package com.app.threetier.mapper;

import com.app.threetier.domain.MemberVO;
import com.app.threetier.domain.OAuthVO;
import com.app.threetier.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {
//    아이디 검사
    public Optional<MemberDTO> selectByMemberEmail(String memberEmail);
//    회원가입
    public void insert(MemberDTO memberDTO);
//    oauth
    public void insertOauth(OAuthVO oAuthVO);
//    로그인
    public Optional<MemberVO> selectMemberForLogin(MemberDTO memberDTO);
}













