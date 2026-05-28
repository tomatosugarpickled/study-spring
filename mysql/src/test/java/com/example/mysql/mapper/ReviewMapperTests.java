package com.example.mysql.mapper;

import com.example.mysql.dto.PostDTO;
import com.example.mysql.dto.ReviewDTO;
import com.example.mysql.dto.ReviewMemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class ReviewMapperTests {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private ReviewMapper reviewMapper;

    @Test
    public void testInsert() {
        PostDTO postDTO = new PostDTO();
        ReviewDTO reviewDTO = new ReviewDTO();

        postDTO.setPostTitle("테스트 제목1");
        postDTO.setPostContent("테스트 내용1");
        postDTO.setMemberId(2L);

        postMapper.insert(postDTO);

        reviewDTO.setId(postDTO.getId());
        reviewDTO.setReviewRate("최고 좋음");

        reviewMapper.insert(reviewDTO.toReviewVO());
    }

    @Test
    public void testSelectById() {
        Optional<ReviewDTO> foundReview = reviewMapper.selectById(5L);

        log.info("{}", foundReview.orElse(null));
    }

    @Test
    public void testSelectWithMemberById() {
        Optional<ReviewMemberDTO> foundMember = reviewMapper.selectWithMemberById(5L);
        log.info("{}", foundMember.orElse(null));
    }
    
    @Test
    public void testSelectAll() {
        List<ReviewMemberDTO> reviews = reviewMapper.selectAll();
        reviews.stream().map(ReviewMemberDTO::toString).forEach(log::info);
    }
}


















