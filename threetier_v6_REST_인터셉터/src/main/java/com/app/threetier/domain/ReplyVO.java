package com.app.threetier.domain;

import com.app.threetier.audit.Period;
import com.app.threetier.common.enumeration.FileContentType;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class ReplyVO extends Period {
    private Long id;
    private String replyContent;
    private Long memberId;
    private Long postId;
}












