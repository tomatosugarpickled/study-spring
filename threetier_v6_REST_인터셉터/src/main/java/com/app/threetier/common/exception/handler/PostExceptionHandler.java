package com.app.threetier.common.exception.handler;

import com.app.threetier.common.exception.FileNotFoundException;
import com.app.threetier.common.exception.LoginFailException;
import com.app.threetier.common.exception.PostNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.net.http.HttpRequest;

@ControllerAdvice(basePackages = "com.app.threetier.controller.post")
public class PostExceptionHandler {
    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(PostNotFoundException.class)
    protected RedirectView postNotFound(PostNotFoundException postNotFoundException){
        return new RedirectView("/post/list");
    }
    @ExceptionHandler(FileNotFoundException.class)
    protected RedirectView fileNotFound(FileNotFoundException fileNotFoundException){
        fileNotFoundException.printStackTrace();
        String url = request.getHeader("referer");
        return new RedirectView(url);
    }
}













