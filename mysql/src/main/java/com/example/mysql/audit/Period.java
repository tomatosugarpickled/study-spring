package com.example.mysql.audit;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public abstract class Period {
    private String createdDatetime;
    private String updatedDatetime;
}
