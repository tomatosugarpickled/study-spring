package com.example.mysql.dto;

import com.example.mysql.domain.PostVO;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String postTitle;
    private String postContent;
    private int postReadCount;
    private Long memberId;
    private String createdDatetime;
    private String updatedDatetime;

    public PostVO toVO(){
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
}
















