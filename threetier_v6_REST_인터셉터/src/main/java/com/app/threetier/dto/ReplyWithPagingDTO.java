package com.app.threetier.dto;

import com.app.threetier.common.pagination.Criteria;
import com.app.threetier.domain.ReplyVO;
import lombok.*;

import java.util.List;

@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class ReplyWithPagingDTO {
    private List<ReplyDTO> replies;
    private Criteria criteria;
}












