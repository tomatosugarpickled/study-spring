# App 프로젝트 흐름 및 파일 역할 정리

## 1. 문서 목적

이 문서는 현재 `C:\gb_0900_hsh\spring\workspace\app` 프로젝트를 기준으로, 화면에서 DB까지 이어지는 요청 흐름과 각 파일의 책임을 상세히 정리한 문서다.

현재 코드를 기준으로 확인한 결과:

- `Controller` 계층이 없다.
- `templates`, `static` 디렉터리는 비어 있다.
- 실제 구현 흐름은 전통적인 `화면 -> Controller -> Service -> Repository -> Mapper -> DB`보다 `요청 -> Spring Security -> JWT/Redis -> UserDetailsService -> DAO -> Mapper -> DB`에 가깝다.
- 일부 파일은 미완성 또는 불일치 상태다. 이 문서에는 그 상태도 함께 기록한다.

---

## 2. 현재 프로젝트 구조 요약

### 핵심 패키지

```text
src/main/java/com/app/app
├─ AppApplication.java
├─ audit
│  └─ Period.java
├─ auth
│  ├─ AuthenticationFilter.java
│  ├─ AuthenticationHandler.java
│  ├─ AuthorizationHandler.java
│  ├─ CustomUserDetails.java
│  └─ JwtTokenProvider.java
├─ common/enumeration
│  ├─ MemberRole.java
│  ├─ OAuthProvider.java
│  └─ Status.java
├─ config
│  ├─ RedisConfig.java
│  └─ SecurityConfig.java
├─ domain
│  ├─ MemberVO.java
│  └─ OAuthVO.java
├─ dto
│  ├─ MemberDTO.java
│  └─ OAuthDTO.java
├─ mapper
│  ├─ MemberMapper.java
│  └─ OAuthMapper.java
├─ repository
│  ├─ MemberDAO.java
│  └─ OAuthDAO.java
└─ service
   └─ CustomUserDetailService.java
```

### 리소스 구조

```text
src/main/resources
├─ application.yml
├─ application.yaml
├─ mapper
│  ├─ MemberMapper.xml
│  └─ OAuthMapper.xml
├─ sql
│  └─ member.sql
├─ static
└─ templates
```

### 테스트 및 잔여 파일

```text
src/test/java/com/app/app/AppApplicationTests.java
src/main/java/com/example/app/AppApplication.java
src/test/java/com/example/app/AppApplicationTests.java
```

`com/example/app` 패키지는 스프링 이니셜라이저 기본 템플릿이 남아 있는 것으로 보이며, 현재 실제 패키지인 `com.app.app`과 중복된다.

---

## 3. 전체 흐름 요약

### 3.1 이상적인 웹 애플리케이션 흐름

현재 프로젝트가 완성되면 일반적으로 아래 흐름을 기대할 수 있다.

```text
브라우저/화면
→ Controller
→ Service
→ Repository(DAO)
→ MyBatis Mapper 인터페이스
→ MyBatis XML
→ PostgreSQL
```

하지만 현재 구현은 아래에 더 가깝다.

### 3.2 현재 실제 구현 흐름

```text
브라우저/클라이언트 요청
→ SecurityConfig
→ AuthenticationFilter
→ JwtTokenProvider
→ CustomUserDetailService
→ MemberDAO
→ MemberMapper
→ MemberMapper.xml
→ PostgreSQL(tbl_member)
```

리프레시 토큰이 개입될 경우에는 Redis도 함께 사용된다.

```text
브라우저/클라이언트 요청
→ AuthenticationFilter
→ JwtTokenProvider
→ RedisTemplate / Redis
→ 새 Access Token 발급
→ SecurityContext 인증 저장
```

---

## 4. 화면에서 DB까지 흐름도

## 4.1 현재 상태 기준 화면 계층

현재 `src/main/resources/templates`와 `src/main/resources/static` 안에는 파일이 없다.

즉, 프로젝트 내부에 다음 파일들은 아직 없다.

- HTML/Thymeleaf 템플릿
- JavaScript 프론트 코드
- CSS 파일
- Controller

그래서 "화면에서 DB까지"의 완전한 MVC 흐름은 아직 미구현 상태다.

현재 문맥에서 "화면"은 외부 클라이언트가 요청을 보내는 출발점으로 해석하는 것이 정확하다.

### 4.2 인증이 필요한 일반 요청 흐름

```text
[클라이언트/화면]
    ↓ HTTP 요청
[SecurityConfig.java]
    ↓ 보안 정책 적용 시도
[AuthenticationFilter.java]
    ↓ accessToken 추출
[JwtTokenProvider.java]
    ↓ 토큰 검증 및 사용자 식별
[CustomUserDetailService.java]
    ↓ 이메일 기준 회원 조회
[MemberDAO.java]
    ↓ DAO 계층 위임
[MemberMapper.java]
    ↓ MyBatis 인터페이스 호출
[MemberMapper.xml]
    ↓ SQL 실행
[PostgreSQL tbl_member]
```

### 4.3 access token 없는 경우의 재발급 흐름

```text
[클라이언트/화면]
    ↓ 요청
[AuthenticationFilter.java]
    ↓ refreshToken 쿠키 확인
[JwtTokenProvider.java]
    ↓ Redis 저장 refreshToken 조회
[RedisConfig.java]
    ↓ RedisTemplate 사용
[Redis 서버]
    ↓ refreshToken 일치 여부 확인
[JwtTokenProvider.java]
    ↓ 새 accessToken / refreshToken 발급
    ↓ 쿠키 저장 및 Authorization 헤더 설정
[SecurityContextHolder]
    ↓ 인증 상태 저장
[다음 필터/요청 처리]
```

### 4.4 OAuth 로그인 관련 DB 조회 흐름

현재 OAuth용 Controller/Service는 없지만 DB 접근 경로는 준비되어 있다.

```text
[클라이언트/화면]
    ↓
(현재 없음: OAuth Controller)
    ↓
(현재 없음: OAuth Service)
    ↓
[OAuthDAO.java]
    ↓
[OAuthMapper.java]
    ↓
[OAuthMapper.xml]
    ↓
[PostgreSQL tbl_member + tbl_oauth]
```

---

## 5. 흐름 단계별 상세 설명

## 5.1 앱 시작

### `src/main/java/com/app/app/AppApplication.java`

- `@SpringBootApplication`이 붙은 메인 실행 클래스다.
- `SpringApplication.run()`으로 스프링 컨테이너를 부팅한다.
- 프로젝트 전체 빈 스캔과 자동 설정의 시작점이다.

### 시작 시 함께 읽히는 설정

#### `src/main/resources/application.yml`

- 서버 포트 `10000` 설정
- PostgreSQL 접속 정보 설정
- Redis 접속 정보 설정
- MyBatis XML 위치와 타입 별칭 설정
- JWT 비밀키 설정

#### `src/main/resources/application.yaml`

- `application.yml`와 거의 동일한 설정이 중복 존재한다.
- `jwt.secret` 값이 서로 다르다.
- 스프링 부트는 `application.yml`와 `application.yaml`를 모두 설정 소스로 인식할 수 있으므로, 실제 적용 우선순위와 충돌 가능성을 점검할 필요가 있다.

---

## 5.2 보안 진입점

### `src/main/java/com/app/app/config/SecurityConfig.java`

이 파일의 의도는 다음과 같다.

- CSRF 비활성화
- 세션 정책을 `STATELESS`로 변경
- 요청 URL별 접근 권한 설정

하지만 현재 상태는 미완성이다.

- `requestMatchers("")` 형태로 빈 문자열만 들어 있다.
- `hasRole("")`도 빈 값이다.
- 메서드 마지막이 `return null;`이다.
- 정상적인 Spring Security 설정이라면 `http.build()`를 반환해야 한다.

즉, 문서상으로는 보안 흐름의 시작점이지만, 현재 코드만 놓고 보면 실제 동작 가능한 완성 상태는 아니다.

---

## 5.3 요청 필터 단계

### `src/main/java/com/app/app/auth/AuthenticationFilter.java`

이 파일은 요청마다 한 번 실행되는 `OncePerRequestFilter` 기반 인증 필터다.

#### 처리 순서

1. 요청에서 access token을 꺼낸다.
2. access token이 있으면:
   - 유효성 검증
   - 블랙리스트 여부 확인
   - 사용자 인증 객체 생성
   - `SecurityContextHolder`에 인증 저장
3. access token이 없으면:
   - 쿠키에서 refresh token 확인
   - Redis에 저장된 refresh token과 비교
   - 유효하면 access token 재발급
   - 새 refresh token도 재발급
   - 인증 객체를 `SecurityContextHolder`에 저장
4. 마지막에 `filterChain.doFilter()`로 다음 필터에 요청을 넘긴다.

#### 이 파일이 실제로 연결하는 하위 파일

- `JwtTokenProvider.java`
- `CustomUserDetailService.java`
- `MemberDAO.java`
- `RedisConfig.java`
- Redis 서버

즉, 이 필터가 현재 프로젝트의 핵심 요청 흐름을 묶는 중심 파일이다.

---

## 5.4 JWT 처리 단계

### `src/main/java/com/app/app/auth/JwtTokenProvider.java`

이 파일은 JWT 관련 실질적인 비즈니스 로직을 담당한다.

#### 담당 로직

- `secretKey`를 Base64 디코딩해서 서명 키 초기화
- access token 생성
- refresh token 생성
- JWT 유효성 검증
- 토큰에서 사용자 이메일 추출
- 토큰 기반 `Authentication` 객체 생성
- 요청 헤더 또는 쿠키에서 access token 추출
- refresh token을 Redis에 저장
- 로그아웃된 토큰을 블랙리스트로 Redis에 저장
- 블랙리스트 등록 여부 확인

#### DB와 연결되는 지점

이 파일은 직접 DB에 접근하지는 않지만, 아래 흐름으로 DB 접근을 유도한다.

```text
JwtTokenProvider.getAuthentication()
→ UserDetailsService.loadUserByUsername()
→ CustomUserDetailService
→ MemberDAO
→ MemberMapper
→ MemberMapper.xml
→ tbl_member
```

#### Redis와 연결되는 지점

```text
JwtTokenProvider.createRefreshToken()
→ RedisTemplate.opsForValue().set()

JwtTokenProvider.getRefreshTokenFromRedis()
→ RedisTemplate.opsForValue().get()

JwtTokenProvider.addToBlacklist()
→ RedisTemplate.opsForValue().set()
```

즉, 이 파일은 PostgreSQL로 가는 인증 조회 흐름과 Redis로 가는 토큰 저장 흐름을 동시에 연결한다.

---

## 5.5 인증 실패/인가 실패 처리

### `src/main/java/com/app/app/auth/AuthenticationHandler.java`

- 인증되지 않은 사용자가 보호 자원에 접근했을 때 동작한다.
- `/api/`로 시작하는 요청이면 `401 Unauthorized`를 반환한다.
- 그 외 요청이면 `/member/login`으로 리다이렉트한다.

### `src/main/java/com/app/app/auth/AuthorizationHandler.java`

- 인증은 되었지만 권한이 부족한 경우 동작한다.
- `/api/` 요청이면 에러를 반환한다.
- 일반 페이지 요청이면 `/member/login`으로 리다이렉트한다.

이 두 파일은 DB 조회를 직접 하지 않으며, 보안 예외 상황의 응답 방향을 결정한다.

---

## 5.6 사용자 인증 객체 생성 단계

### `src/main/java/com/app/app/service/CustomUserDetailService.java`

- Spring Security의 `UserDetailsService` 구현체다.
- 이메일(username)로 회원을 조회한다.
- 조회 실패 시 `UsernameNotFoundException`을 던진다.
- 조회 성공 시 `CustomUserDetails` 객체를 반환한다.

#### 호출 흐름

```text
AuthenticationFilter
→ JwtTokenProvider.getAuthentication()
→ CustomUserDetailService.loadUserByUsername()
→ MemberDAO.findMemberByMemberEmail()
```

### `src/main/java/com/app/app/auth/CustomUserDetails.java`

- `MemberDTO`를 Spring Security가 이해하는 `UserDetails` 형태로 바꾼다.
- 권한 목록은 `MemberRole.getAuthorities()`를 사용한다.
- `getUsername()`은 `memberEmail`을 반환한다.
- `getPassword()`는 `memberPassword`를 반환한다.

즉, DB에서 읽어온 회원 정보가 Spring Security 인증 문맥으로 바뀌는 지점이다.

---

## 5.7 Repository(DAO) 단계

### `src/main/java/com/app/app/repository/MemberDAO.java`

이 파일은 회원 관련 DB 접근을 한 단계 감싸는 DAO다.

#### 메서드별 역할

- `save(MemberDTO memberDTO)`
  - 회원가입용 insert 위임
- `findMemberForLogin(MemberVO memberVO)`
  - 일반 로그인용 회원 조회 위임
- `findMemberByMemberEmail(String memberEmail)`
  - 이메일 기준 회원 조회 위임

실제 SQL은 직접 쓰지 않고 `MemberMapper`에 위임한다.

### `src/main/java/com/app/app/repository/OAuthDAO.java`

이 파일은 OAuth 관련 DB 접근 DAO다.

#### 메서드별 역할

- `save(OAuthVO oAuthVO)`
  - OAuth 연동 정보 저장
- `findMemberForLogin(MemberDTO memberDTO)`
  - OAuth 조건이 포함된 회원 조회
- `findMemberByMemberEmail(String memberEmail, OAuthProvider provider)`
  - 이메일과 provider 기준 회원 조회

현재 이 DAO를 호출하는 상위 Service/Controller는 아직 없다.

---

## 5.8 Mapper 인터페이스 단계

### `src/main/java/com/app/app/mapper/MemberMapper.java`

MyBatis가 구현하는 인터페이스다.

- `insert(MemberDTO memberDTO)`
- `selectMemberForLogin(MemberVO memberVO)`
- `selectMemberByMemberEmail(String memberEmail)`

### `src/main/java/com/app/app/mapper/OAuthMapper.java`

OAuth용 MyBatis 인터페이스다.

- `insert(OAuthVO oAuthVO)`
- `selectMemberForLogin(MemberDTO memberDTO)`
- `selectMemberByMemberEmail(String memberEmail, OAuthProvider provider)`

`@Param`으로 파라미터 이름을 명시해서 XML에서 사용할 수 있게 했다.

---

## 5.9 MyBatis XML 단계

### `src/main/resources/mapper/MemberMapper.xml`

이 파일에는 `tbl_member`를 대상으로 하는 SQL이 있다.

#### 들어 있는 SQL

- `insert`
  - 회원 이름, 이메일, 비밀번호 저장
- `selectMemberForLogin`
  - 이메일, 비밀번호, `member_status = 'active'` 조건으로 로그인용 조회
- `selectMemberByMemberEmail`
  - 이메일 기준 회원 조회

#### 현재 주의점

`namespace="com.app.oauth.mapper.MemberMapper"`로 선언되어 있다.

하지만 실제 인터페이스 경로는:

```text
com.app.app.mapper.MemberMapper
```

즉, XML namespace와 Java 인터페이스 패키지가 불일치한다.

### `src/main/resources/mapper/OAuthMapper.xml`

이 파일에는 `tbl_member`와 `tbl_oauth`를 조인하는 SQL이 있다.

#### 들어 있는 SQL

- `insert`
  - OAuth 연동 정보 저장
- `selectMemberForLogin`
  - 회원 + OAuth 조건으로 로그인용 조회
- `selectMemberByMemberEmail`
  - 이메일 + provider 기준 OAuth 회원 조회

#### 현재 주의점

이 파일도 namespace가 다음처럼 선언되어 있다.

```text
com.app.oauth.mapper.OAuthMapper
```

하지만 실제 인터페이스는:

```text
com.app.app.mapper.OAuthMapper
```

즉, 이 XML도 패키지명이 맞지 않는다.

---

## 5.10 실제 DB 스키마

### `src/main/resources/sql/member.sql`

이 파일은 PostgreSQL 기준 테이블 및 enum 타입 생성 스크립트다.

#### 정의 내용

- enum 타입
  - `status`
  - `member_role`
  - `oauth_provider`
- 테이블
  - `tbl_member`
  - `tbl_oauth`

#### 테이블 관계

```text
tbl_member (1)
    ↑
    └── member_id
tbl_oauth (N)
```

즉, 한 회원에 대해 여러 OAuth 연동을 둘 수 있는 구조로 읽힌다.

---

## 6. 회원 관련 데이터 객체 흐름

## 6.1 공통 시간 필드

### `src/main/java/com/app/app/audit/Period.java`

- `createdDatetime`
- `updatedDatetime`

두 필드를 공통으로 가지는 추상 부모 클래스다.

## 6.2 도메인 객체

### `src/main/java/com/app/app/domain/MemberVO.java`

- 회원 테이블에 대응하는 VO
- `Period`를 상속
- 회원 이름, 이메일, 비밀번호, 상태, 권한 등을 가진다

### `src/main/java/com/app/app/domain/OAuthVO.java`

- OAuth 테이블에 대응하는 VO
- `Period`를 상속
- provider id, provider, profileURL, memberId를 가진다

## 6.3 DTO 객체

### `src/main/java/com/app/app/dto/MemberDTO.java`

- 화면/요청/응답에 쓰일 회원 DTO 역할
- `toMemberVO()`로 회원 VO 변환
- `toOAuthVO()`로 OAuth VO 변환
- `memberPassword`는 `WRITE_ONLY`로 설정되어 있어 직렬화 시 응답으로는 제외된다

### `src/main/java/com/app/app/dto/OAuthDTO.java`

- OAuth DTO 역할
- `toOAuthVO()`를 통해 도메인 객체로 변환한다

현재 상위 Controller와 Service는 없지만, 추후 화면이나 API에서 입력받은 데이터를 이 DTO가 수용하고 DAO 계층 전달용 VO로 바꿀 구조다.

---

## 7. Enum 및 권한 흐름

### `src/main/java/com/app/app/common/enumeration/Status.java`

- 회원 상태 enum
- 문자열 값과 enum 상호 변환 로직 포함

### `src/main/java/com/app/app/common/enumeration/OAuthProvider.java`

- `kakao`, `naver` provider enum
- 문자열 값과 enum 변환 지원

### `src/main/java/com/app/app/common/enumeration/MemberRole.java`

- `ADMIN`, `MEMBER` 권한 enum
- `getAuthorities()`에서 Spring Security용 `ROLE_ADMIN`, `ROLE_MEMBER` 형식 권한 객체를 만든다

흐름상으로는 다음처럼 사용된다.

```text
DB member_role 컬럼
→ MemberDTO.memberRole
→ CustomUserDetails.getAuthorities()
→ Spring Security 권한 판단
```

---

## 8. 현재 구현 기준 시나리오별 흐름

## 8.1 JWT 인증이 필요한 요청

```text
클라이언트 요청
→ AuthenticationFilter
→ JwtTokenProvider.parseTokenFromHeader()
→ JwtTokenProvider.validateToken()
→ JwtTokenProvider.isTokenInBlacklist()
→ JwtTokenProvider.getAuthentication()
→ CustomUserDetailService.loadUserByUsername()
→ MemberDAO.findMemberByMemberEmail()
→ MemberMapper.selectMemberByMemberEmail()
→ MemberMapper.xml selectMemberByMemberEmail
→ tbl_member 조회
→ UserDetails 반환
→ SecurityContextHolder 저장
→ 다음 처리 진행
```

## 8.2 Access Token 만료, Refresh Token 존재

```text
클라이언트 요청
→ AuthenticationFilter
→ accessToken 없음
→ 쿠키에서 refreshToken 확인
→ JwtTokenProvider.getUsername(refreshToken)
→ JwtTokenProvider.checkRefreshTokenBetweenCookieAndRedis()
→ Redis refresh token 조회
→ refreshToken 유효성 검증
→ JwtTokenProvider.getAuthentication()
→ MemberDAO를 통한 회원 조회
→ 새 accessToken 생성
→ 새 refreshToken 생성
→ 응답 헤더와 쿠키 갱신
→ SecurityContextHolder 저장
```

## 8.3 일반 로그인용 DB 조회 준비 상태

구현된 메서드는 있지만, 현재 이를 호출하는 Controller/Service는 없다.

```text
(예상 미래 흐름)
화면 로그인 폼
→ Controller
→ Service
→ MemberDAO.findMemberForLogin()
→ MemberMapper.selectMemberForLogin()
→ MemberMapper.xml
→ tbl_member
```

## 8.4 OAuth 로그인용 DB 조회 준비 상태

이 역시 DB 계층만 준비된 상태다.

```text
(예상 미래 흐름)
OAuth 콜백 요청
→ OAuth Controller
→ OAuth Service
→ OAuthDAO.findMemberByMemberEmail(...)
→ OAuthMapper.selectMemberByMemberEmail(...)
→ OAuthMapper.xml
→ tbl_member + tbl_oauth
```

---

## 9. 파일별 역할 상세 리스트

## 9.1 시작 및 설정 파일

- `src/main/java/com/app/app/AppApplication.java`
  - 스프링 부트 시작점
- `src/main/java/com/app/app/config/SecurityConfig.java`
  - 보안 필터 체인 설정 의도, 현재 미완성
- `src/main/java/com/app/app/config/RedisConfig.java`
  - Redis 캐시 매니저 및 RedisTemplate 등록
- `src/main/resources/application.yml`
  - 메인 실행 설정
- `src/main/resources/application.yaml`
  - 중복 설정 파일
- `build.gradle`
  - 프로젝트 의존성 및 빌드 설정
- `settings.gradle`
  - 프로젝트 이름 설정

## 9.2 인증 관련 파일

- `src/main/java/com/app/app/auth/AuthenticationFilter.java`
  - 요청별 JWT 검사 및 재발급 처리
- `src/main/java/com/app/app/auth/JwtTokenProvider.java`
  - JWT 생성, 검증, Redis 연동
- `src/main/java/com/app/app/auth/AuthenticationHandler.java`
  - 인증 실패 처리
- `src/main/java/com/app/app/auth/AuthorizationHandler.java`
  - 권한 실패 처리
- `src/main/java/com/app/app/auth/CustomUserDetails.java`
  - Spring Security 사용자 정보 래핑
- `src/main/java/com/app/app/service/CustomUserDetailService.java`
  - 이메일 기반 회원 조회 서비스

## 9.3 데이터 계층 파일

- `src/main/java/com/app/app/repository/MemberDAO.java`
  - 회원 DAO
- `src/main/java/com/app/app/repository/OAuthDAO.java`
  - OAuth DAO
- `src/main/java/com/app/app/mapper/MemberMapper.java`
  - 회원 MyBatis 인터페이스
- `src/main/java/com/app/app/mapper/OAuthMapper.java`
  - OAuth MyBatis 인터페이스
- `src/main/resources/mapper/MemberMapper.xml`
  - 회원 SQL
- `src/main/resources/mapper/OAuthMapper.xml`
  - OAuth SQL
- `src/main/resources/sql/member.sql`
  - DB 스키마 정의

## 9.4 도메인/DTO/공통 파일

- `src/main/java/com/app/app/audit/Period.java`
  - 공통 생성/수정 시간 필드
- `src/main/java/com/app/app/domain/MemberVO.java`
  - 회원 도메인 객체
- `src/main/java/com/app/app/domain/OAuthVO.java`
  - OAuth 도메인 객체
- `src/main/java/com/app/app/dto/MemberDTO.java`
  - 회원 DTO 및 변환 메서드
- `src/main/java/com/app/app/dto/OAuthDTO.java`
  - OAuth DTO 및 변환 메서드
- `src/main/java/com/app/app/common/enumeration/Status.java`
  - 상태 enum
- `src/main/java/com/app/app/common/enumeration/MemberRole.java`
  - 권한 enum
- `src/main/java/com/app/app/common/enumeration/OAuthProvider.java`
  - OAuth 제공자 enum

## 9.5 테스트 및 잔여 파일

- `src/test/java/com/app/app/AppApplicationTests.java`
  - 컨텍스트 로딩 테스트
- `src/main/java/com/example/app/AppApplication.java`
  - 템플릿 잔여 메인 클래스
- `src/test/java/com/example/app/AppApplicationTests.java`
  - 템플릿 잔여 테스트
- `HELP.md`
  - 기본 도움말 문서

---

## 10. 현재 구조에서 비어 있거나 빠진 부분

현재 문서화 과정에서 확인된 공백은 다음과 같다.

- `controller` 패키지가 없다.
- 일반 비즈니스 서비스 계층이 없다.
- `templates` 폴더에 화면 파일이 없다.
- `static` 폴더에 정적 리소스가 없다.
- 회원가입/로그인/OAuth 콜백을 받는 엔드포인트가 없다.
- 인증 필터를 `SecurityFilterChain`에 명시적으로 연결한 흔적이 없다.

즉, 데이터 계층과 인증 유틸은 어느 정도 준비되어 있지만, 화면/API 진입점은 아직 연결되지 않았다.

---

## 11. 현재 코드에서 보이는 주요 주의점

## 11.1 SecurityConfig 미완성

- `return null` 상태
- URL 매처와 권한 값이 비어 있음

실행 시 보안 설정이 정상 동작하지 않을 가능성이 높다.

## 11.2 MyBatis XML namespace 불일치

- `MemberMapper.xml`
  - XML: `com.app.oauth.mapper.MemberMapper`
  - 실제 인터페이스: `com.app.app.mapper.MemberMapper`
- `OAuthMapper.xml`
  - XML: `com.app.oauth.mapper.OAuthMapper`
  - 실제 인터페이스: `com.app.app.mapper.OAuthMapper`

이 상태면 MyBatis 매핑이 실패할 가능성이 높다.

## 11.3 설정 파일 중복

- `application.yml`
- `application.yaml`

동일 성격의 파일이 둘 존재하며 JWT secret 값이 다르다.

## 11.4 템플릿 패키지 중복

- `com.app.app`
- `com.example.app`

실행과 테스트에서 혼란을 만들 수 있다.

---

## 12. 최종 정리

현재 프로젝트는 "회원 + OAuth + JWT + Redis" 인증 기반의 백엔드 구조를 준비하는 단계로 보인다.

이미 구현된 축은 다음과 같다.

- JWT 생성/검증
- Redis 기반 refresh token 저장/조회
- Redis 기반 블랙리스트 처리
- 회원 조회용 DAO/Mapper/XML 구조
- OAuth 조회용 DAO/Mapper/XML 구조
- Spring Security용 `UserDetailsService` 구현

아직 비어 있는 축은 다음과 같다.

- 화면 파일
- Controller
- 일반 서비스 계층
- 실제 로그인/회원가입/OAuth 진입 API
- 완성된 Security 설정

따라서 현재 "화면에서 DB까지"의 실체는 완전한 MVC 흐름이 아니라, 인증 필터를 중심으로 한 보안/조회 흐름이라고 이해하는 것이 가장 정확하다.
