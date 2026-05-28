package com.example.mysql.controller;

import com.example.mysql.dto.MemberDTO;
import com.example.mysql.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/member/**")
@RequiredArgsConstructor
public class MemberController {
    private final MemberMapper memberMapper;

//    회원가입
    @GetMapping("join")
    public String goToJoinForm(){
        return "member/join";
    }

    @PostMapping("join")
    public RedirectView join(MemberDTO memberDTO){
        memberMapper.insert(memberDTO.toVO());
        return new RedirectView("/member/login");
    }

//    로그인
    @GetMapping("login")
    public String goToLoginForm(){
        return "member/login";
    }
}



















