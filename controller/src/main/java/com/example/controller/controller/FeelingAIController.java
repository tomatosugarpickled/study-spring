package com.example.controller.controller;

import com.example.controller.domain.FeelingCheckResponse;
import com.example.controller.domain.SpamCheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/ai/**")
public class FeelingAIController {

    private final WebClient webClient = WebClient.create("http://localhost:8000");

    @GetMapping("feeling-check")
    public String gotoFeelingCheck(){
        return "/feeling-check";
    }

    @PostMapping("feeling-check")
    @ResponseBody
    public Mono<FeelingCheckResponse> feeling(@RequestBody Map<String, String> body){
        String feeling = body.get("feeling");
        log.info("feeling : {}", feeling);

        return webClient.post()
                .uri("/api/feeling-check")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(FeelingCheckResponse.class); // 비동기로 결과를 받음
    }

}
