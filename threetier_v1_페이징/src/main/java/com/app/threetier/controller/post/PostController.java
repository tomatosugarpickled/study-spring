package com.app.threetier.controller.post;

import com.app.threetier.dto.MemberDTO;
import com.app.threetier.dto.PostDTO;
import com.app.threetier.service.post.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
@RequestMapping("/post/**")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final HttpSession session;
    private final PostService postService;

    @GetMapping("write")
    public String goToWriteForm(){
        return "post/write";
    }

    @PostMapping("write")
    public RedirectView write(PostDTO postDTO,
                                     @RequestParam("file") ArrayList<MultipartFile> multipartFiles){
//        MemberDTO member = (MemberDTO) session.getAttribute("member");
//        postDTO.setMemberId(member.getId());
        postService.write(postDTO, multipartFiles);
        return null;
    }

    @GetMapping("list/{page}")
    public String list(@PathVariable int page, Model model){
//        model.addAttribute("postsWithPaging", postService.list(page));
        return "post/list";
    }
}










