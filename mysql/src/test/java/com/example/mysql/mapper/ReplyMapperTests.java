package com.example.mysql.mapper;

import com.example.mysql.dto.ReplyDTO;
import com.example.mysql.dto.ReplyWithReReplyDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class ReplyMapperTests {
    @Autowired
    private ReplyMapper replyMapper;

    @Test
    public void testInsert(){
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setReplyContent("테스트 댓글3");
        replyDTO.setMemberId(2L);
        replyDTO.setReviewId(5L);

        replyMapper.insert(replyDTO.toVO());
    }

    @Test
    public void testInsertReReply(){
        Optional<ReplyDTO> foundReply = replyMapper.selectById(4L);

        foundReply.ifPresent(replyDTO -> {
            log.info(replyDTO.toString());
            int depth = replyMapper.selectDepth(replyDTO.getId());
            log.info("{}..........", depth);
//            Integer depth = replyMapper.selectDepth(replyDTO.getId());
//            if(depth == null) {
//                depth = 2;
//            }
            ReplyDTO reply = new ReplyDTO();
            reply.setReplyContent("테스트 대댓글3");
            reply.setMemberId(2L);
            reply.setReviewId(5L);
            reply.setParentId(replyDTO.getId());
            reply.setDepth(depth);
            replyMapper.insertReReply(reply.toVO());
        });
    }

    @Test
    public void testSelectAllByReviewId(){
        List<ReplyWithReReplyDTO> replyWithReReplyDTOs = new ArrayList<>();
        List<ReplyDTO> replyDTOS = replyMapper.selectAllByReviewId(5L);
        ReplyWithReReplyDTO replyWithReReplyDTO = null;

        for(int i=0; i<replyDTOS.size();i++){
            replyWithReReplyDTO = new ReplyWithReReplyDTO();
            List<ReplyDTO> rereplyDTOS = replyMapper.selectAllRereplyByReplyId(replyDTOS.get(i).getId());
            log.info("{}...", replyDTOS.get(i).getId());
            log.info("{}", replyDTOS.toString());
            replyWithReReplyDTO.setReply(replyDTOS.get(i));
            replyWithReReplyDTO.setReReplies(rereplyDTOS);
            replyWithReReplyDTOs.add(replyWithReReplyDTO);
        }

        replyWithReReplyDTOs.stream().map(ReplyWithReReplyDTO::toString).forEach(log::info);
    }

    @Test
    public void testUpdate(){
        Optional<ReplyDTO> foundReply = replyMapper.selectById(3L);
        foundReply.ifPresent(replyDTO -> {
            replyDTO.setReplyContent("수정된 대댓글2");
            replyMapper.update(replyDTO.toVO());
        });
    }

    @Test
    public void testDelete(){
        Optional<ReplyDTO> foundReply = replyMapper.selectById(11L);
        foundReply.ifPresent(replyDTO -> {
            List<ReplyDTO> reReplies = replyMapper.selectAllRereplyByReplyId(replyDTO.getId());
            if(reReplies.size() > 0) {
                replyDTO.setReplyContent("삭제된 댓글입니다.");
                return;
            }
            replyMapper.delete(replyDTO.getId());
        });
    }
}
















