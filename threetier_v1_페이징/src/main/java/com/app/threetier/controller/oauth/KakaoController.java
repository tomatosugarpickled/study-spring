package com.app.threetier.controller.oauth;

import com.app.threetier.dto.MemberDTO;
import com.app.threetier.service.member.MemberService;
import com.app.threetier.service.oauth.KaKaoService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@RequiredArgsConstructor
public class KakaoController {
    private final KaKaoService kaKaoService;
    private final MemberService memberService;
    private final HttpSession session;

    @GetMapping("/kakao/login")
    public RedirectView kakaoLogin(String code, HttpServletResponse response, RedirectAttributes redirectAttributes){
        MemberDTO memberDTO = kaKaoService.kakaoLogin(code);
        String path = null;

        if(memberDTO.getId() == null){
            redirectAttributes.addFlashAttribute("kakao", memberDTO);
            path = "/member/kakao/join";
        }else {
            session.setAttribute("member", memberDTO);
            path = "/post/list/1";
        }

        return new RedirectView(path);
    }
}














