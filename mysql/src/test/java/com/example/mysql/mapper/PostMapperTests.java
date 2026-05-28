package com.example.mysql.mapper;

import com.example.mysql.domain.PostVO;
import com.example.mysql.dto.PostDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PostMapperTests {
    @Autowired
    private PostMapper postMapper;

    @Test
    public void testInsert() {
        PostDTO postDTO = new PostDTO();

        postDTO.setPostTitle("테스트 제목2");
        postDTO.setPostContent("테스트 내용2");
        postDTO.setMemberId(2L);

        postMapper.insert(postDTO);

        log.info("{}", postDTO.getId());
    }
}


















