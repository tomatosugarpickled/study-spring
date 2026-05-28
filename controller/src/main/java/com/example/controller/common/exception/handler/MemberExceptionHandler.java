package com.example.controller.common.exception.handler;

import com.example.controller.common.exception.member.LoginFailException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.example.controller")
public class MemberExceptionHandler {

    @ExceptionHandler(LoginFailException.class)
    protected String handleLoginFailException(LoginFailException e){
        return "/ex/login";
    }
}
