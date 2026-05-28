package com.app.threetier.dto;

import com.app.threetier.common.pagination.Criteria;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostWithPagingDTO {
    private List<PostDTO> posts;
    private Criteria criteria;
}
