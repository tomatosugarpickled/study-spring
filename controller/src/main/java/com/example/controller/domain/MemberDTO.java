package com.example.controller.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String memberEmail;
//    transient: 일시적인, 곧 사라질, 서버단에서 사용할 때(세션 및 RedisDB)
    @JsonIgnore // REST API에서 사용할 때 제거
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // 화면에서 받는 건 가능
    private /*transient*/ String memberPassword;
    private String memberName;
}
