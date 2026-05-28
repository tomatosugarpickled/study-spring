package com.example.mysql.dto;

import com.example.mysql.common.enumeration.Status;
import com.example.mysql.domain.MemberVO;
import lombok.*;

@NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private Status memberStatus;
    private String createdDatetime;
    private String updatedDatetime;

    public MemberVO toVO() {
        return MemberVO.builder()
                .id(id)
                .memberEmail(memberEmail)
                .memberPassword(memberPassword)
                .memberName(memberName)
                .memberStatus(memberStatus)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}

















