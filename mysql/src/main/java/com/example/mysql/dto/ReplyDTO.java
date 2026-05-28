package com.example.mysql.dto;

import com.example.mysql.audit.Period;
import com.example.mysql.domain.ReplyVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class ReplyDTO {
    private Long id;
    private Long parentId;
    private String replyContent;
    private int depth;
    private Long memberId;
    private Long reviewId;
    private String createdDatetime;
    private String updatedDatetime;

    public ReplyVO toVO(){
        return ReplyVO.builder()
                .id(id)
                .parentId(parentId)
                .replyContent(replyContent)
                .depth(depth)
                .memberId(memberId)
                .reviewId(reviewId)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}















