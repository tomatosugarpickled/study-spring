package com.app.threetier.dto;

import com.app.threetier.audit.Period;
import com.app.threetier.domain.ReplyVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class ReplyDTO {
    private Long id;
    private String replyContent;
    private Long memberId;
    private Long postId;
    private String createdDatetime;
    private String updatedDatetime;
    private String memberName;

    public ReplyVO toVO(){
        return ReplyVO.builder()
                .id(id)
                .replyContent(replyContent)
                .memberId(memberId)
                .postId(postId)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}












