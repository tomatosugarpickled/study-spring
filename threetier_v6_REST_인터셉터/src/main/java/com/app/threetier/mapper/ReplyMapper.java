package com.app.threetier.mapper;

import com.app.threetier.common.pagination.Criteria;
import com.app.threetier.domain.ReplyVO;
import com.app.threetier.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {
//    추가
    public void insert(ReplyVO replyVO);

//    목록
    public List<ReplyDTO> selectAllByPostId(@Param("criteria") Criteria criteria, @Param("id") Long id);
//    전체 개수
    public int selectCountAllByPostId(Long id);

//    수정
    public void update(ReplyVO replyVO);

//    삭제
    public void delete(Long id);
    public void deleteAllByPostId(Long id);
}













