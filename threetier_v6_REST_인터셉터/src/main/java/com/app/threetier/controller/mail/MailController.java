package com.app.threetier.controller.mail;

import com.app.threetier.service.mail.MailService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/mail/**")
@RequiredArgsConstructor
@Slf4j
public class MailController {
    private final MailService mailService;
    private final HttpServletResponse response;

    @GetMapping("send")
    public String send(){
        return "/mail/send";
    }

    @PostMapping("send")
    public RedirectView send(String email) {
        mailService.sendMail(email, response);
        return new RedirectView("/mail/send-ok");
    }

    @GetMapping("send-ok")
    public String sendOk(){
        return "/mail/send-ok";
    }

    @GetMapping("confirm")
    public RedirectView confirm(@CookieValue(value = "code", required = false) String cookieCode, String code){
        if(cookieCode == null || cookieCode.isEmpty()) {
            log.info("인증 코드 만료");
            return new RedirectView("/mail/fail");
        }

        if(cookieCode.equals(code)){
            Cookie cookie = new Cookie("code", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            return new RedirectView("/mail/success");
        }
        return new RedirectView("/mail/fail");
    }

    @GetMapping("success")
    public String success(){
        return "/mail/success";
    }

    @GetMapping("fail")
    public String fail(){
        return "/mail/fail";
    }
}


















