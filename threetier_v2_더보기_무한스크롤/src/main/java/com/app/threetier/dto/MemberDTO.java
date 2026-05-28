package com.app.threetier.dto;

import com.app.threetier.audit.Period;
import com.app.threetier.common.enumeration.Provider;
import com.app.threetier.common.enumeration.Status;
import com.app.threetier.domain.MemberVO;
import com.app.threetier.domain.OAuthVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class MemberDTO {

    private Long id;
    private String memberEmail;
    @JsonIgnore
    private String memberPassword;
    private String memberName;
    private Status memberStatus;
    private String createdDatetime;
    private String updatedDatetime;
    private Provider provider;
    private boolean remember;
    private String profileImageUrl;

    public MemberVO toMemberVO() {
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

    public OAuthVO toOAuthVO() {
        return OAuthVO.builder().id(id).provider(provider).build();
    }
}















