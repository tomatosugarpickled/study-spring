package com.app.threetier.mapper;

import com.app.threetier.common.pagination.Criteria;
import com.app.threetier.common.search.Search;
import com.app.threetier.domain.PostVO;
import com.app.threetier.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
//    추가
    public void insert(PostDTO postDTO);
//    목록
    public List<PostDTO> selectAll(@Param("criteria") Criteria criteria, @Param("search") Search search);
//    전체 개수
    public int selectTotal(@Param("search") Search search);
//    조회
    public Optional<PostDTO> selectById(Long id);
//    수정
    public void update(PostVO postVO);
//    삭제
    public void delete(Long id);
}
