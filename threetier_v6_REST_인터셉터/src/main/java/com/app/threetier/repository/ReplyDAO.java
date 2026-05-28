package com.app.threetier.repository;

import com.app.threetier.common.pagination.Criteria;
import com.app.threetier.domain.ReplyVO;
import com.app.threetier.dto.ReplyDTO;
import com.app.threetier.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {
    private final ReplyMapper replyMapper;

//    추가
    public void save(ReplyVO replyVO){
        replyMapper.insert(replyVO);
    }

//    목록
    public List<ReplyDTO> findAllByPostId(Criteria criteria, Long id){
        return replyMapper.selectAllByPostId(criteria, id);
    }

//    전체 개수
    public int findCountAllByPostId(Long id){
        return replyMapper.selectCountAllByPostId(id);
    }

//    수정
    public void setReply(ReplyVO replyVO){
        replyMapper.update(replyVO);
    }

//    삭제
    public void delete(Long id){
        replyMapper.delete(id);
    }

    public void deleteAllByPostId(Long id){
        replyMapper.deleteAllByPostId(id);
    }
}
