package com.app.threetier.service.reply;

import com.app.threetier.common.pagination.Criteria;
import com.app.threetier.domain.ReplyVO;
import com.app.threetier.dto.ReplyDTO;
import com.app.threetier.dto.ReplyWithPagingDTO;
import com.app.threetier.repository.ReplyDAO;
import com.app.threetier.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class  ReplyService {
    private final ReplyDAO replyDAO;

//    추가
    public void write(ReplyDTO replyDTO){
        replyDAO.save(replyDTO.toVO());
    }

//    목록
    public ReplyWithPagingDTO getListInPost(int page, Long id){
        ReplyWithPagingDTO replyWithPagingDTO = new ReplyWithPagingDTO();
        Criteria criteria = new Criteria(page, replyDAO.findCountAllByPostId(id));
        List<ReplyDTO> replies = replyDAO.findAllByPostId(criteria, id);
        replies.forEach((reply) -> {
            reply.setCreatedDatetime(DateUtils.toRelativeTime(reply.getCreatedDatetime()));
        });

        replyWithPagingDTO.setCriteria(criteria);
        replyWithPagingDTO.setReplies(replies);

        return replyWithPagingDTO;
    }

//    수정
    public void update(ReplyDTO replyDTO){
        replyDAO.setReply(replyDTO.toVO());
    }

//    삭제
    public void delete(Long id){
        replyDAO.delete(id);
    }

    public void deleteAllByPostId(Long id){
        replyDAO.deleteAllByPostId(id);
    }
}
