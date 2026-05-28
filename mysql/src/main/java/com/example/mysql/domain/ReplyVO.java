package com.example.mysql.domain;

import com.example.mysql.audit.Period;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @ToString(callSuper = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class ReplyVO extends Period {
    private Long id;
    private Long parentId;
    private String replyContent;
    private int depth;
    private Long memberId;
    private Long reviewId;
}
