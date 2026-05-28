package com.app.app.mapper;

import com.app.app.common.enumeration.OAuthProvider;
import com.app.app.domain.OAuthVO;
import com.app.app.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface OAuthMapper {
//    회원가입
    public void insert(OAuthVO oAuthVO);
//    로그인
    public Optional<MemberDTO> selectMemberForLogin(MemberDTO memberDTO);
//    이메일로 조회
    public Optional<MemberDTO> selectMemberByMemberEmail(@Param("memberEmail") String memberEmail,
                                                         @Param("provider") OAuthProvider provider);
}
