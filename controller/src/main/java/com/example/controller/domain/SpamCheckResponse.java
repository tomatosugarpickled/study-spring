package com.example.controller.domain;

import lombok.Data;

@Data
public class SpamCheckResponse {
//    FastAPI에서 받은 필드와 동일한 이름으로 설정
//    기본 자료형 X, 클래스 자료형으로 사용해야한다.
    private Boolean isSpam;

}
