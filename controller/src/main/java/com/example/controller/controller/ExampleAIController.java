package com.example.controller.controller;

import com.example.controller.domain.MemberDTO;
import com.example.controller.domain.SpamCheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/ai/**")
public class ExampleAIController {
    private final WebClient webClient = WebClient.create("https://localhost:8000");

    @GetMapping("")
    public String ai(){
        return "ai";
    }

    @PostMapping("")
    @ResponseBody
    public Mono<SpamCheckResponse> ai(@RequestBody Map<String, String> body){
        String message = body.get("message");
        log.info("message : {}", message);

        return webClient.post()
                .uri("/api/spam-check")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(SpamCheckResponse.class); // 비동기로 결과를 받음
    }

}
