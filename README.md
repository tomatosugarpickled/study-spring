# Spring

Spring Framework / Spring Boot / Spring Security 학습 정리.

## 프로젝트 구성

- [app](./app/) — Spring Boot 기본 프로젝트
- [controller](./controller/) — 컨트롤러와 요청 매핑 실습
- [dependency](./dependency/) — 의존성 주입(DI) 실습
- [mysql](./mysql/) — MyBatis 기반 MySQL 연동 실습
- [threetier_v1_페이징](./threetier_v1_페이징/) — 3계층 게시판 v1 (페이징)
- [threetier_v2_더보기_무한스크롤](./threetier_v2_더보기_무한스크롤/) — v2 (더보기·무한스크롤)
- [threetier_v3_필터](./threetier_v3_필터/) — v3 (검색 필터)
- [threetier_v4_수정_삭제_조회_카카오로그아웃](./threetier_v4_수정_삭제_조회_카카오로그아웃/) — v4 (CRUD 완성·카카오 로그아웃)
- [threetier_v5_이메일_SMS](./threetier_v5_이메일_SMS/) — v5 (이메일·SMS 발송)
- [threetier_v6_REST_인터셉터](./threetier_v6_REST_인터셉터/) — v6 (REST API·인터셉터)
- [app_day03_oauth](./app_day03_oauth/) — OAuth 소셜 로그인 실습
- [app_day04_s3_swagger](./app_day04_s3_swagger/) — S3 업로드·Swagger 문서화 실습

3계층 게시판(threetier)은 같은 프로젝트를 기능 단위로 버전을 올려가며 확장한 실습이다.

## Framework

- **라이브러리** — 개발자가 필요한 기능을 직접 호출해서 사용하는 코드 묶음.
- **API** — 기능을 사용하기 위해 외부에 공개된 규칙 또는 인터페이스.
- **프레임워크** — 애플리케이션의 기본 구조와 실행 흐름을 제공하고, 개발자가 정해진 지점에 코드를 채워 넣는 개발 기반.

### Framework 장점

개발에 필요한 구조를 이미 코드로 만들어 놓았기 때문에, 실력이 부족한 개발자라 하더라도 반쯤 완성된 상태에서 필요한 부분을 조립하는 형태의 개발이 가능하다.
회사 입장에서는 프레임워크를 사용하면 일정한 품질이 보장되는 결과물을 얻을 수 있고, 개발자 입장에서는 완성된 구조에 자신이 맡은 서비스에 대한 코드를 개발해서 넣기 때문에 개발 시간을 단축할 수 있다.

---

## Spring Framework

경량 프레임워크.

예전 프레임워크는 다양한 경우를 처리하기 위해 여러 기능을 넣다 보니 하나의 기능을 위해서 아주 많은 구조가 필요했다.
기술이 너무나 복잡하고 방대했기 때문에, 전체를 이해하고 개발하기에는 어려움이 많았다.
그래서 Spring Framework가 등장했고, 특정 기능을 위주로 간단한 JAR 파일 등을 선택하여 모든 개발이 가능하도록 구성되어 있다.

### Spring Framework의 특징

- POJO 기반의 구성
- AOP 지원
- Transaction 관리
- 편리한 MVC 구조
- WAS에 종속적이지 않은 개발 환경
- DI를 통한 객체 간의 관계 구성

### POJO 기반의 구성

**Plain Old Java Object**.
오래된 방식의 간단한 자바 객체라는 의미이며, JAVA 코드에서 일반적으로 객체를 구성하는 방식을 Spring Framework에서 그대로 사용할 수 있다는 의미이다.

### AOP 지원

관점 지향 프로그래밍.
좋은 개발 환경에서는 개발자가 비지니스 로직에만 집중할 수 있게 한다.
Spring Framework는 반복적인 코드를 분리해줌으로써 핵심 비지니스 로직에만 집중할 수 있는 방법을 제공한다.

보안이나 로그, 트랜잭션, 예외처리와 같이 비지니스 로직은 아니지만 반드시 처리가 필요한 부분을 **주변 로직(횡단 관심사)** 이라고 하고, 개발해야 할 서비스는 **핵심 로직(종단 관심사)** 이라고 한다.
Spring Framework는 이러한 횡단 관심사를 분리해서 설계하는 것이 가능하고, 횡단 관심사를 모듈로 분리하는 프로그래밍을 AOP라고 한다.

핵심 비지니스 로직에만 집중하여 코드 개발이 가능해지고, 각 프로젝트마다 다른 관심사 적용 시 코드 수정을 최소화할 수 있으며, 원하는 관심사의 유지보수가 수월한 코드로 구성이 가능해진다.

### Transaction 관리

DB 작업 시, 트랜잭션을 매번 상황에 맞게 관리하지 않고 어노테이션을 사용하여 트랜잭션 영역을 구성한 뒤 특정 예외 발생 시 자동으로 롤백을 처리하거나 커밋을 처리한다.

### WAS에 종속적이지 않은 개발 환경

전체 Application을 실행하지 않아도 기능별 단위 테스트가 용이하기 때문에 버그를 줄이고 개발 시간을 단축할 수 있다.

### ★ DI를 통한 객체 간의 관계 구성

**의존성 (Dependency)** 이란 하나의 객체가 다른 객체 없이 제대로 된 역할을 할 수 없다는 것을 의미한다.
예를 들어 A 객체가 B 객체 없이 동작이 불가능한 상황을 "A가 B에 의존적이다"라고 표현한다.
하지만 직접 A 필드에 B 객체를 선언하면 결합성이 단단해지기 때문에 유연성이 떨어진다.

**주입 (Injection)** 은 외부에서 내부로 밀어 넣는 것을 의미한다.
필요한 객체를 외부에서 밀어 넣어 유연성을 높이고 결합성을 느슨하게 해준다.
주입을 받는 입장에서는 어떤 객체인지 신경 쓸 필요가 없고 어떤 객체에 의존하든 자신의 역할은 변하지 않는다.

```
의존성
   A →→→→→→→→→→→→→→→ B
   A 필드에 B 객체를 직접 생성

의존성 주입
   A ↔↔↔↔↔↔ ? ↔↔↔↔↔↔ B
   A는 B가 필요하다고 신호를 보내고,
   ?가 B 객체를 외부에서 생성하여 주입한다.
```

Spring Framework에서는 **ApplicationContext** 가 `?` 이며, 필요한 객체들을 생성 및 주입해주는 역할을 한다.
따라서 개발자들은 기존의 프로그래밍과는 달리 객체와 객체를 분리해서 생성하고, 이러한 객체를 엮는 **wiring** 작업의 형태로 개발하게 된다.

`ApplicationContext` 가 관리하는 객체들을 **빈 (Bean)** 이라 부르고, 이는 **Spring Container (Bean Container)** 에 저장된다.

---

## Spring Boot

Spring Framework를 사용함에 있어서 초기 설정 및 필요한 라이브러리에 대한 설정의 어려움이 많으며, 시간이 너무 오래 걸린다.
따라서 자동 설정과 개발에 필요한 모든 것을 관리해주는 **Spring Boot** 를 선호한다.
각 코어 및 라이브러리의 버전들도 맞춰야 하지만 Spring Boot를 사용하면 이러한 복잡성을 해결하기에도 좋다.

### 프로젝트 기본 경로

| 경로 | 용도 |
|---|---|
| `src/main/java` | 서버단 JAVA 파일 |
| `src/test/java` | 단위 테스트 JAVA 파일 |
| `src/main/resources` | 설정 파일 및 뷰단 |
| `src/main/resources/static` | css, js, image 등 정적 파일 경로 |
| `src/main/resources/templates` | html 파일 경로 |
| `build.gradle` | 라이브러리 관리 |
| `application.yml` | Spring의 모든 설정 |

---

## 의존성 주입 실습

`Food`, `Knife` 두 개의 클래스 간의 관계를 구성한 뒤 의존성 주입을 통해 해당 객체 통합 테스트 진행.

## Qualifier

`@Autowired` 를 통해 객체를 주입할 때, 같은 타입의 객체가 여러 개 있다면 구분할 수 없다.
이 때, `@Qualifier` 를 통해 식별자를 설정하면 원하는 객체를 주입받을 수 있다.

### Qualifier 실습

`Restaurant.java`, `Outback.java`, `Vips.java` 세 객체를 선언한 뒤 상속관계를 판단하여 하나의 객체를 인터페이스로 선언한다.
각 레스토랑에 셀바 이용 가능 여부와 스테이크 가격을 필드로 구성한다.
스테이크 가격은 항상 똑같지만 각 레스토랑에서 변경 가능하다.
기본 레스토랑은 아웃백으로 설정한다.

---

## Spring MVC (Front-Controller Pattern)

```
                    HandlerMapping
REQUEST  ────①────►     ②↕                ③                 ④
                  ↔ DispatcherServlet ↔ HandlerAdapter ↔ Controller
RESPONSE ◄───⑦───  ⑥↕      ⑤↕
                   View    ViewResolver
                    ↕
                HTML 및 기타
```

### Spring MVC 패턴의 특징

- `HttpServletRequest`, `HttpServletResponse` 직접 사용을 지양한다.
- 다양한 타입의 파라미터 처리, 다양한 타입의 리턴 타입 사용 가능.
- GET 방식, POST 방식 등의 전송 방식에 대한 처리를 어노테이션으로 처리한다.
- 상속·인터페이스 방식 대신 어노테이션으로만 설정 가능.

---

## REST

**Representational State Transfer**.

언제 어디서든 누구든 서버에 요청을 보낼 때 URI만으로도 데이터 또는 행위(CRUD) 상태를 이해할 수 있도록 설계하는 규칙.

1. **소문자로 작성한다.**
   대문자로 작성 시 문제가 발생할 수 있기 때문에 소문자로 작성한다.

2. **언더바 대신 하이픈을 사용한다.**
   가독성을 높이기 위해서 하이픈으로 구분하는 것이 좋다.

3. **URI 마지막에 슬래시를 작성하지 않는다.**
   마지막에 작성하는 슬래시는 의미가 없다.

4. **계층 관계 표현 시 슬래시를 구분자로 사용한다.**
   계층 관계(포함 관계)에서는 슬래시로 구분해준다.

5. **파일 확장자는 포함시키지 않는다.**
   파일 확장자는 URI로 표현하지 않고 Header의 Content-Type을 사용하여 body의 내용을 처리하도록 설계한다.

6. **행위(동사)는 URI에 담지 않고, HTTP Method (GET, POST, PUT, DELETE)로 표현한다.**
   - `http://www.app.com/members/delete/1` (X)
   - `DELETE http://www.app.com/members/1` (O)

7. **URI에 사용되는 영어 단어는 복수로 작성한다.** 데이터의 집합을 의미하기 때문이다.
   - `DELETE http://www.app.com/member/1` (X)
   - `DELETE http://www.app.com/members/1` (O)

### HTTP Method

| Method | 의미 |
|---|---|
| `GET` | 조회 (Read) |
| `POST` | 생성 (Create) |
| `PUT` | 전체 수정 (Update) |
| `PATCH` | 일부 수정 (Update) |
| `DELETE` | 삭제 (Delete) |

---

## AOP (Aspect Oriented Programming)

관점이란 개발에 있어서 **관심사 (Concern)** 를 의미한다.
코드의 중복을 줄여주고, 핵심 로직과 주변 로직을 분리하여 관리할 수 있다.

- 파라미터가 잘 전달 되었는가?
- 이 로직에서 발생할 수 있는 예외가 무엇인가?

핵심 로직은 아니지만 반복적으로 개발에 필요한 관심사들을 주변 로직이라고 한다.
따라서 AOP는 이러한 주변 로직을 횡단(주변) 관심사로 분리하여 작성하고, 종단 관심사인 핵심 비지니스 로직만을 작성하도록 한다.

즉, 반복적으로 나타나는 횡단 관심사를 모듈로 분리한 후 적절한 시점에 로직을 주입하는 것이 AOP이다.
스프링에서는 별도의 복잡한 설정 없이 간편하게 AOP의 기능들을 구현할 수 있기 때문에 중요한 특징 중 하나이다.

### AOP 용어

- **Aspect** — 무엇을 어디에서 할 것인가? 여러 곳에서 공통적으로 사용하는 주변 로직 모듈의 묶음.
- **Advice** — 어떤 동작을 언제 할 것인가? 실제 실행될 코드를 작성해 놓은 모듈.
- **Joinpoint** — 적용 가능한 모든 시점, Advice가 적용될 수 있는 시점.
- **Pointcut** — 실제 작업을 수행할 타겟을 정의하는 필터.
- **proxy** — 타겟을 가로채 대신 실행.

비유:

| 역할 | 비유 |
|---|---|
| Target | 연예인 |
| Proxy | 매니저 |
| Aspect | 매니저가 해야 할 일을 정의한 문서 |
| Joinpoint | 연예인에게 진행 가능한 모든 스케줄 (영화, 광고 등) |
| Pointcut | 연예인이 광고만 진행하겠다고 함 |
| Advice | 매니저가 계약서 작성 |

### Advice 종류

- `Around` (전 구역)
- `Before` (메소드 시작 직후)
- `After` (메소드 종료 직전)
- `AfterReturning` (메소드 리턴 후)
- `AfterThrowing` (메소드 예외 발생 후)

### AOP 설계 순서

1. 구현할 횡단 관심사를 의미할 수 있는 어노테이션 만들기.
2. 어노테이션을 AOP로 등록하기.
3. 종단 관심사에 등록된 어노테이션 사용하기.

---

## 개발 흐름

```
xml → mapper → 통합테스트 → dao → service → 통합테스트 → controller
```

```
테이블 확인 (없으면 만들기)
VO, DTO
mapper.xml
Mapper.java
통합 테스트
DAO.java
Service.java
통합 테스트
Controller.java
HTML
```

화면이 더해진 흐름 :

```
mapper.xml
Mapper.java
테스트
DAO.java
Service.java
테스트
Controller.java
service.js
layout.js
event.js
HTML
```

### 관계 모델링

- **1:1**
- **1:N** — 1 안에 N
- **N:N** — VO 3개, DTO 3개

### 게시글 작성

1. **게시글** (먼저 작업)

2. **태그** (List라면 `[i].필드명`)
   - 동적으로 추가되는 것.
   - 마지막에 몰아서 처리 (완료 버튼).
   - Array는 우리의 친구.

3. **파일** (`MultipartFile`)
   - 한 개를 받든, 여러 개를 받든지 서버로 작동.
   - 아무것도 전달하지 않으면, `input type="file"` 개수만큼 들어간다.
   - 단, 파일 정보는 `""` 또는 `null`.
   - 파일 여러 개 : `List<MultipartFile>`, `MultipartFile[]`

---

## Spring Security (앱 보안)

Spring 기반 애플리케이션의 보안을 담당하는 프레임워크로서 **인증 (Authentication)** 과 **인가 (Authorization)** 를 쉽게 처리할 수 있게 해준다.

## JWT (JSON Web Token)

JSON 형태의 토큰으로, 서버와 클라이언트 간에 인증 정보를 안전하게 주고받을 때 사용한다.

### JWT 특징

1. 자체적으로 인증 정보를 포함한다.
2. 토큰 자체가 인증 수단 + 정보이기 때문에 세션을 강제하지 않는다 (stateless, 무상태).
3. 서명 (signature)으로 위변조를 방지한다.

### JWT 서명

```
JWT = Header + Payload + Signature
```

- **Header** — 토큰 타입과 암호화 알고리즘 정보.
- **Payload** — 사용자 정보, 만료시간 등.
- **Signature** — Header + Payload를 기반으로 서명값을 만들어 변조 여부를 확인.

Signature는 Header와 Payload가 변조되지 않았다는 걸 증명하는 코드이다.
Header와 Payload를 특정 알고리즘(HMAC, RSA 등)과 키를 사용해 서명값으로 만든다.
서버는 클라이언트가 보낸 JWT를 받을 때, 같은 방식으로 Header와 Payload를 사용해 다시 서명을 만들고 JWT에 붙어온 서명 (Signature)과 새로 만든 서명이 같으면 조작된 게 없다고 판단한다.
만약 다르다면 위변조된 것으로 간주하고, 토큰이 거절된다.

### Spring Security + JWT 구성

- **JwtTokenProvider** — JWT 생성, 서명, 검증 기능을 담당하도록 직접 구현한 컴포넌트.
- **AuthenticationFilter** — 로그인 요청을 가로채 인증을 시도하고, 인증 성공 시 JWT를 생성하여 클라이언트에 전달하도록 구성한 필터.
- **UserDetailService** — 사용자 정보 조회 및 인증 처리를 담당하며, 실제 사용자 정보를 관리한다.
- **SecurityConfig** — 보안 설정 (필터 체인, 인증 방식, 접근 제한, 권한 설정 등).

### 인증 흐름

1. **인증 요청** — 사용자가 ID/PW로 로그인 요청.
2. **JWT 생성** — 인증에 성공하면 JWT 생성 후 클라이언트에 전달.
3. **요청 시 JWT 포함** — 클라이언트는 이후 요청 헤더에 JWT를 포함시켜서 전송.
4. **JWT 검증** — 서버가 JWT 유효성 검증 후 인증 처리.
5. **정보 접근** — 인증된 사용자일지라도 권한이 충분한지 검사 후 접근 허용.

### CSRF / CORS

- **CSRF** — Cross-Site Request Forgery. 사용자가 의도하지 않은 요청을 인증된 상태로 보내게 만드는 공격.
- **CORS** — 브라우저에서 서로 다른 출처(origin) 간 요청을 허용할지 결정하는 정책. origin은 프로토콜, 호스트, 포트까지 포함한다.

### Token

| 종류 | 기간 |
|---|---|
| Access Token | 기간 짧음 |
| Refresh Token | 기간 긴 편 |
