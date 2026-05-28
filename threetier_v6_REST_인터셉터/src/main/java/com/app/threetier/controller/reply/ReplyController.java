package com.app.threetier.controller.reply;

import com.app.threetier.dto.ReplyDTO;
import com.app.threetier.dto.ReplyWithPagingDTO;
import com.app.threetier.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/replies/**")
public class ReplyController {
    private final ReplyService replyService;

//    추가
    @PostMapping("write")
    public void write(@RequestBody ReplyDTO replyDTO){
        replyService.write(replyDTO);
    }

//    목록
    @GetMapping("list/{page}")
    public ReplyWithPagingDTO list(@PathVariable int page, Long postId){
        return replyService.getListInPost(page, postId);
    }

//    수정
//    수정해야하는 개수가 서로 다르면, 많은 쪽이 PUT, 적은 쪽이 PATCH
    @PutMapping("{id}")
    public void update(@RequestBody ReplyDTO replyDTO){
        replyService.update(replyDTO);
    }

//    삭제
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        replyService.delete(id);
    }
}


















