package com.example.mysql.domain;

import com.example.mysql.audit.Period;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Getter @ToString(callSuper = true)
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class PostVO extends Period {
    private Long id;
    private String postTitle;
    private String postContent;
    private int postReadCount;
    private Long memberId;
}








