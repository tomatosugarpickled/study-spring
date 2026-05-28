package com.example.mysql.domain;

import com.example.mysql.common.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;

//  VO 혹은 DTO는 의존성 주입 요청을 할 필요가 없기 때문에 Bean Container에서 담아놓을 필요가 없다.
//@Component

// 무분별한 객체 생성을 막으면서도 프레임워크(내부적으로)는 접근 가능하게 함.
// MyBatis에서 VO객체에 결과를 담을 때 항상 기본 생성자를 호출하여 메모리에 올린다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// @Builder가 내부적으로 사용하는 생성자
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @ToString
@EqualsAndHashCode(of="id")
@Builder
public class MemberVO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private Status memberStatus;
    private String createdDatetime;
    private String updatedDatetime;

////    @Builder
////    #####################################################################
//    public static MemberVOBuilder builder() {
////        MemberVO를 만들기 위해 MemberVOBuilder를 사용하지만,
////        static을 붙이지 않으면 MemberVOBuilder를 쓰기 위해 MemberVO를 먼저 만들어야 한다.
////        return new MemberVO().new MemberVOBuilder();
////        따라서 내부 클래스에 static을 붙여 외부 객체 없이 바로 생성할 수 있게 한다.
//        return new MemberVOBuilder();
//    }
//
////    캡슐화를 위해 내부 클래스로 선언한다.
////    MemberVOBuilder는 MemberVO에서만 사용하기 때문에 다른 곳에서 보이지 않도록 숨겨놓는다.
////    내부 클래스일 때에만 class 앞에 static을 붙일 수 있다.
////    외부 객체 없이 바로 생성 가능하며, 독립적이다.
////    독립성을 갖기 때문에 숨겨진 외부 객체와 연결(숨은 참조)되어있지 않아서 메모리 누수가 발생하지 않는다.
////    ※ 내부 클래스에서 외부 객체 참조시 외부 객체 메모리 해제 불가
//    public static class MemberVOBuilder {
////        MemberVO 객체는 아직 만들어지지 않았기 때문에 임시저장할 필드가 독립적으로 필요하다.
//        private Long id;
//        private String memberEmail;
//        private String memberPassword;
//        private String memberName;
//        private Status memberStatus;
//        private String createdDatetime;
//        private String updatedDatetime;
//
//        public MemberVOBuilder() {;}
//
//        public MemberVOBuilder id(Long id) {
//            this.id = id;
//            return this;
//        }
//
//        public MemberVOBuilder memberEmail(String memberEmail) {
//            this.memberEmail = memberEmail;
//            return this;
//        }
//
//        public MemberVOBuilder memberPassword(String memberPassword) {
//            this.memberPassword = memberPassword;
//            return this;
//        }
//
//        public MemberVOBuilder memberName(String memberName) {
//            this.memberName = memberName;
//            return this;
//        }
//
//        public MemberVOBuilder memberStatus(Status memberStatus) {
//            this.memberStatus = memberStatus;
//            return this;
//        }
//
//        public MemberVOBuilder createdDatetime(String createdDatetime) {
//            this.createdDatetime = createdDatetime;
//            return this;
//        }
//
//        public MemberVOBuilder updatedDatetime(String updatedDatetime) {
//            this.updatedDatetime = updatedDatetime;
//            return this;
//        }
//
//        public MemberVO build() {
////            @AllArgsConstructor로 만든 생성자 호출
//            return new MemberVO(
//                    id,
//                    memberEmail,
//                    memberPassword,
//                    memberName,
//                    memberStatus,
//                    createdDatetime,
//                    updatedDatetime
//            );
//        }
////    #####################################################################
//    }
}





















