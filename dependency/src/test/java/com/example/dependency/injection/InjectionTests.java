package com.example.dependency.injection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
// 통합 테스트: 설정, DB 연동, API 호출, 보안
//@SpringBootTest
// 단위 테스트: 비지니스 로직, 제어문, 계산, 유효성 검사
public class InjectionTests {
//    @Autowired
//    private Coding coding;

    @Test
    public void codingTest(){
        Computer computer = new Computer();
        Coding coding = new Coding(computer);
        log.info("{}", coding);

//        assertThat(coding).isNotNull();
    }
}















