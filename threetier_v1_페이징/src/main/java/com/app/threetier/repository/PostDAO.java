package com.app.threetier.repository;

import com.app.threetier.common.pagination.Criteria;
import com.app.threetier.dto.PostDTO;
import com.app.threetier.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostDAO {
    private final PostMapper postMapper;

//    추가
    public void save(PostDTO postDTO) {
        postMapper.insert(postDTO);
    }

//    목록
    public List<PostDTO> findAll(Criteria criteria){
        return postMapper.selectAll(criteria);
    }

//    전체 개수
    public int findTotal(){
        return postMapper.selectTotal();
    }

}
















