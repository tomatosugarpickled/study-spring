package com.app.threetier.controller.member;

import com.app.threetier.dto.MemberDTO;
import com.app.threetier.service.member.MemberService;
import com.app.threetier.service.oauth.KaKaoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/member/**")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
//    session: 서버에 저장(공용)
    private final HttpSession session;
    private final KaKaoService kaKaoService;

    @GetMapping("check-email")
    @ResponseBody
    public boolean checkEmail(String memberEmail){
        return memberService.checkEmail(memberEmail);
    }

    @GetMapping("join")
    public String goToJoinForm(){
        return "member/join";
    }

    @GetMapping("kakao/join")
    public String goToKakaoJoinForm(){
        return "member/kakao/join";
    }

    @PostMapping("join")
    public RedirectView join(MemberDTO memberDTO){
        memberService.join(memberDTO);
        return new RedirectView("/member/login");
    }

    @PostMapping("kakao/join")
    public RedirectView kakaoJoin(MemberDTO memberDTO){
        memberService.kakaoJoin(memberDTO);
        return new RedirectView("/member/login");
    }

    @GetMapping("login")
    public String goToLoginForm(@CookieValue(name="remember", required = false) boolean remember,
                                @CookieValue(name="remember-member-email", required = false) String rememberMemberEmail,
                                HttpServletRequest request,
                                Model model){
        model.addAttribute("remember", remember);
        model.addAttribute("rememberMemberEmail", rememberMemberEmail);
        return "member/login";
    }

    @PostMapping("login")
    public RedirectView login(MemberDTO memberDTO, Model model, HttpServletResponse response){
        session.setAttribute("member", memberService.login(memberDTO));

        Cookie rememberMemberEmailCookie = new Cookie("remember-member-email", memberDTO.getMemberEmail());
        Cookie rememberCookie = new Cookie("remember", String.valueOf(memberDTO.isRemember()));

        rememberMemberEmailCookie.setPath("/");
        rememberCookie.setPath("/");

        if(memberDTO.isRemember()){
            rememberMemberEmailCookie.setMaxAge(60 * 60 * 24 * 30); // 30일 유지
            rememberCookie.setMaxAge(60 * 60 * 24 * 30); // 30일 유지

        }else{
            rememberMemberEmailCookie.setMaxAge(0);
            rememberCookie.setMaxAge(0);
        }

        response.addCookie(rememberMemberEmailCookie);
        response.addCookie(rememberCookie);

//        return new RedirectView("/post/list/1");
        return new RedirectView("/post/list");
    }

    @GetMapping("logout")
    public RedirectView logout(){
        session.invalidate();
        return new RedirectView("/member/login");
    }
}










