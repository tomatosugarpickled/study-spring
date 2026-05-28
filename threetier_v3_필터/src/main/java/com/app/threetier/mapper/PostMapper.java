package com.app.threetier.mapper;

import com.app.threetier.common.pagination.Criteria;
import com.app.threetier.common.search.Search;
import com.app.threetier.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
//    추가
    public void insert(PostDTO postDTO);
//    목록
    public List<PostDTO> selectAll(@Param("criteria") Criteria criteria, @Param("search") Search search);
//    전체 개수
    public int selectTotal(@Param("search") Search search);
}
