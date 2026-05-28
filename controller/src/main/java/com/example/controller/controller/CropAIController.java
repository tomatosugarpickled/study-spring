package com.example.controller.controller;

import com.example.controller.domain.CropCheckResponse;
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
public class CropAIController {

    private final WebClient webClient = WebClient.create("http://localhost:8000");

    @GetMapping("crop-check")
    public String gotoCropCheck(){
        return "/crop-check";
    }

    @PostMapping("crop-check")
    @ResponseBody
    public Mono<CropCheckResponse> crop(@RequestBody Map<String, Object> body){
        log.info("crop payload : {}", body);

        return webClient.post()
                .uri("/api/crop-check")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(CropCheckResponse.class);
    }
}
