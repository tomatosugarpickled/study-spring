package com.example.dependency.injection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ToString @Getter
public class Coding {

//    필드 주입
//    사용 방법이 매우 편리하다.
//    주입된 객체는 불변(immutable) 상태로 만들 수 없기 때문에 외부에서 수정될 위험이 있다.
//    OCP(Open-Closed Principle, 개방-폐쇄 원칙)을 위반할 수 있다.
//    필드 주입은 생성된 이후 시점에 주입이 된다.
//    생성 시점에 순환 참조 발생 여부를 알 수 없다.

//    @Autowired
//    private Computer computer;

//    setter 주입
//    생성 후 주입이 이루어진다.
//    주입된 객체는 불변(immutable) 상태로 만들 수 없기 때문에 외부에서 수정될 위험이 있다.
//    OCP(Open-Closed Principle, 개방-폐쇄 원칙)을 위반할 수 있다.
//    생성 시점에 순환 참조 발생 여부를 알 수 없다.
//    필수가 아닌 선택적 느낌이 있기 때문에 의존성에 부합하지도 않는다.

//    바인딩 : 매핑 + 대입
//          HTTP 요청 시점에 화면으로부터 전달된 데이터를 서버의 객체에 넣는 기술
//    ※ 회면으로부터 값을 받을 때에는 setter 메소드를 통해 데이터를 바인딩하므로
//    주입이 아닌 바인딩 목적으로 사용된다(@Autowired는 작성하지 않는다).
//    private Computer computer;
//
//    @Autowired
//    public void setComputer(Computer computer) {
//        this.computer = computer;
//    }

//  생성자 주입(생성과 동시에 주입)

    private final Computer computer;

}
