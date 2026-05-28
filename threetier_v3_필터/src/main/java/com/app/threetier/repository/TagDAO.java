package com.app.threetier.repository;

import com.app.threetier.domain.TagVO;
import com.app.threetier.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TagDAO {
    private final TagMapper tagMapper;

//    추가
    public void save(TagVO tagVO) {
        tagMapper.insert(tagVO);
    }

//    목록
    public List<TagVO> findAllByPostId(Long id){
        return tagMapper.selectAllByPostId(id);
    }

//    전체 태그(중복 없이)
    public List<String> findAll(){
        return tagMapper.selectAll();
    }
}











