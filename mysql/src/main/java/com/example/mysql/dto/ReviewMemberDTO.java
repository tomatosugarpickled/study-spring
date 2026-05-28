package com.example.mysql.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class ReviewMemberDTO {
    private Long id;
    private String postTitle;
    private String postContent;
    private int postReadCount;
    private Long memberId;
    private String createdDatetime;
    private String updatedDatetime;
    private String reviewRate;
    private String memberName;
}
