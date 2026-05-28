package com.example.mysql.mapper;

import com.example.mysql.domain.PostVO;
import com.example.mysql.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {
//    추가
    public void insert(PostDTO postDTO);
//    수정
    public void update(PostVO postVO);
//    조회수 증가
    public void updatePostReadCount(Long id);
//    삭제
    public void delete(Long id);
}















