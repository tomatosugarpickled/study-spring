package com.example.mysql.dto;

import com.example.mysql.domain.PostVO;
import com.example.mysql.domain.ReviewVO;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private String postTitle;
    private String postContent;
    private int postReadCount;
    private Long memberId;
    private String createdDatetime;
    private String updatedDatetime;
    private String reviewRate;

    public PostVO toPostVO(){
       return PostVO.builder()
                .id(id)
                .postTitle(postTitle)
                .postContent(postContent)
                .postReadCount(postReadCount)
                .memberId(memberId)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }

    public ReviewVO toReviewVO(){
        return ReviewVO.builder().id(id).reviewRate(reviewRate).build();
    }
}
















