package com.app.threetier.repository;

import com.app.threetier.common.pagination.Criteria;
import com.app.threetier.common.search.Search;
import com.app.threetier.domain.PostVO;
import com.app.threetier.dto.PostDTO;
import com.app.threetier.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PostDAO {
    private final PostMapper postMapper;

//    추가
    public void save(PostDTO postDTO) {
        postMapper.insert(postDTO);
    }

//    목록
    public List<PostDTO> findAll(Criteria criteria, Search search){
        return postMapper.selectAll(criteria, search);
    }

//    전체 개수
    public int findTotal(Search search){
        return postMapper.selectTotal(search);
    }

//    조회
    public Optional<PostDTO> findById(Long id){
        return postMapper.selectById(id);
    }

//    수정
    public void setPost(PostVO postVO){
        postMapper.update(postVO);
    }

//    삭제
    public void delete(Long id){
        postMapper.delete(id);
    }
}
















