package com.app.threetier.dto;

import com.app.threetier.domain.TagVO;
import lombok.*;

@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class TagDTO {
    private Long id;
    private String tagName;
    private Long postId;

    public TagVO toVO(){
        return TagVO.builder()
                .id(id)
                .tagName(tagName)
                .postId(postId)
                .build();
    }
}













