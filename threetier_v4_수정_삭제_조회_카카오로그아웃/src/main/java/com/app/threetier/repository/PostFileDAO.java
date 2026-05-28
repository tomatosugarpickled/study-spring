package com.app.threetier.repository;

import com.app.threetier.domain.PostFileVO;
import com.app.threetier.dto.PostFileDTO;
import com.app.threetier.mapper.PostFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostFileDAO {
    private final PostFileMapper postFileMapper;

//    추가
    public void save(PostFileVO postFileVO) {
        postFileMapper.insert(postFileVO);
    }

//    목록
    public List<PostFileDTO>  findAllByPostId(Long id) {
        return postFileMapper.selectAllByPostId(id);
    }

//    삭제
    public void delete(Long id){
        postFileMapper.delete(id);
    }
}













