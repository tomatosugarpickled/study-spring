package com.app.threetier.mapper;

import com.app.threetier.domain.PostFileVO;
import com.app.threetier.dto.PostFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostFileMapper {
//    추가
    public void insert(PostFileVO postFileVO);
//    목록
    public List<PostFileDTO> selectAllByPostId(Long id);
//    삭제
    public void delete(Long id);
//    삭제(게시글 아이디)
    public void deleteByPostId(Long id);
}
