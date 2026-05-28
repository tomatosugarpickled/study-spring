package com.app.threetier.domain;

import com.app.threetier.audit.Period;
import com.app.threetier.common.enumeration.Provider;
import com.app.threetier.common.enumeration.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OAuthVO {
    private Long id;
    private Provider provider;
}















