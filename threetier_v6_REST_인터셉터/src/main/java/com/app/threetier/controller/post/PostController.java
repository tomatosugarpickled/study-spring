package com.app.threetier.controller.post;

import com.app.threetier.common.search.Search;
import com.app.threetier.dto.MemberDTO;
import com.app.threetier.dto.PostDTO;
import com.app.threetier.service.post.PostService;
import com.app.threetier.service.tag.TagService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/post/**")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final HttpSession session;
    private final PostService postService;
    private final TagService tagService;

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
        return new RedirectView("/post/list");
    }

//    @GetMapping("list/{page}")
//    public String list(@PathVariable int page, Model model){
////        model.addAttribute("postsWithPaging", postService.list(page));
//        return "post/list";
//    }

    @GetMapping("list")
    public String list(Model model){
        model.addAttribute("tags", tagService.selectAll());
        return "post/list";
    }

    @GetMapping("update")
    public String goToUpdateForm(Long id, Model model){
        PostDTO post = postService.detail(id);
        model.addAttribute("post", post);
        return "post/update";
    }

    @PostMapping("update")
    public RedirectView update(PostDTO postDTO,
                               @RequestParam("file") List<MultipartFile> multipartFiles,
                               RedirectAttributes redirectAttributes){
        postService.update(postDTO, multipartFiles);
        redirectAttributes.addAttribute("id", postDTO.getId());
        return new RedirectView("/post/detail");
    }

    @GetMapping("detail")
    public String detail(Long id, Model model) {
        model.addAttribute("post", postService.detail(id));
        return "post/detail";
    }

    @GetMapping("delete")
    public RedirectView delete(Long id){
        postService.delete(id);
        return new RedirectView("/post/list");
    }
}










