package com.app.threetier.controller.post;

import com.app.threetier.dto.PostWithPagingDTO;
import com.app.threetier.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts/**")
@RequiredArgsConstructor
@Slf4j
public class PostAPIController {
    private final PostService postService;

    @GetMapping("list/{page}")
    public PostWithPagingDTO list(@PathVariable int page){
        return postService.list(page);
    }
}

















