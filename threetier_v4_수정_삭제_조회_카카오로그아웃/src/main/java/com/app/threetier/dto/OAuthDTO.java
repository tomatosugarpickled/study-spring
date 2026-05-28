package com.app.threetier.dto;

import com.app.threetier.common.enumeration.Provider;
import com.app.threetier.domain.OAuthVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class OAuthDTO {
    private Long id;
    private Provider provider;

    public OAuthVO toVO() {
        return OAuthVO.builder().id(id).provider(provider).build();
    }
}















