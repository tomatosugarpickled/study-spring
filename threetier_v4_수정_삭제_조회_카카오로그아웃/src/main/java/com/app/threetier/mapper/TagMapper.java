package com.app.threetier.mapper;

import com.app.threetier.domain.TagVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {
//    추가
    public void insert(TagVO tagVO);

//    목록
    public List<TagVO> selectAllByPostId(Long id);

//    전체 태그(중복 없이)
    public List<String> selectAll();

//    삭제
    public void delete(Long id);

//    삭제(게시글 아이디)
    public void deleteByPostId(Long id);
}













