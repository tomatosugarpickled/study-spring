package com.example.mysql.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReplyWithReReplyDTO {
    private ReplyDTO reply;
    
//    답글 목록
    private List<ReplyDTO> reReplies;
}
