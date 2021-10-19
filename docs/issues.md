# L.E. Assemble Issues

## 구현 시 겪은 Issue

**CORS Issue**
1. 문제 : localhost:3000(리액트)에서 localhost:8080(스프링부트 API)에 API를 호출할 때 CORS 에러 발생
2. 해결 : 리액트 createProxyMiddleware 설정, SpringBoot에서 addCorsMappings 메소드를 오버라이딩하고, http://localhost:3000에서 오는 요청들을 허용
3. 지금은 리액트를 거둬내어 발생하지 않음

---

**Windows 환경의 mustache 변수 네이밍 Issue**
Windows OS 사용시 userName 으로 값을 주고받으면 윈도우 사용자명이 출력됨

---

**Build cancelled while executing task ':Application.main()'**
Settings -> Gradle -> 우측 하단의 Build and run using, Run tests using을 모두 InteliJ로 바꿔줌

---

**Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'storeService' defined in file**
JPA 레포지토리 내 메소드 이름을 잘못 기재하여 메소드 생성에 실패한 것

---

**Spring Security 적용 후 JUnit5 테스트에서 302코드 반환**
권한 문제로 @WithMockUser(roles = "USER") 사용

---

**com.fasterxml.jackson.databind.JsonMappingException: Infinite recursion (StackOverflowError)**
양방향 매핑 오류

---

**관계를 가지는 엔티티를 저장할 때**
블로그 포스팅 완료

**Spring Security + Ajax 호출 시 CSRF 관련 403 Forbidden 에러**
https://www.popit.kr/spring-security-ajax-%ED%98%B8%EC%B6%9C-%EC%8B%9C-csrf-%EA%B4%80%EB%A0%A8-403-forbidden-%EC%97%90%EB%9F%AC/

**ajax 호출 후 redirect 할 때 발생하는 문제들**
스프링을 안타고 이동하니 시큐리티도 안탐, 302, 405코드 응답

