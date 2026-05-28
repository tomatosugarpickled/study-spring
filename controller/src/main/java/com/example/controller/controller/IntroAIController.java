package com.example.controller.controller;

import com.example.controller.domain.RecommendResponse;
import com.example.controller.domain.UserProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/ai/**")
public class IntroAIController {

    private static final int MY_USER_ID = 15;

    private final WebClient webClient = WebClient.create("http://localhost:8000");

    @GetMapping("intro")
    public String gotoIntro(){
        return "/intro";
    }

    @GetMapping("me")
    @ResponseBody
    public Mono<UserProfile> me(){
        return webClient.get()
                .uri("/api/users/" + MY_USER_ID)
                .retrieve()
                .bodyToMono(UserProfile.class);
    }

    @PostMapping("recommend")
    @ResponseBody
    public Mono<RecommendResponse> recommend(@RequestBody Map<String, Object> body){
        log.info("recommend payload : {}", body);
        body.put("user_id", MY_USER_ID);

        return webClient.post()
                .uri("/api/recommend")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(RecommendResponse.class);
    }
}
