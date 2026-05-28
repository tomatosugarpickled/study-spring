package com.app.threetier.dto;

import com.app.threetier.audit.Period;
import com.app.threetier.common.enumeration.Status;
import com.app.threetier.domain.PostVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String postTitle;
    private String postContent;
    private int postReadCount;
    private Status postStatus;
    private Long memberId;
    private String memberName;
    private String createdDatetime;
    private String updatedDatetime;

    private List<TagDTO> tags = new ArrayList<>();
    private List<PostFileDTO> postFiles = new ArrayList<>();

    public PostVO toVO(){
        return PostVO.builder()
                .id(id)
                .postTitle(postTitle)
                .postContent(postContent)
                .postReadCount(postReadCount)
                .memberId(memberId)
                .postStatus(postStatus)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}













