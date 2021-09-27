# L.E. Assemble Issues

## 구현 시 겪은 Issue

### CORS 관련
localhost:3000(리액트)에서 localhost:8080(스프링부트 API서버)에 api를 호출할 때 CORS 에러 발생  
리액트에서 createProxyMiddleware 설정, SpringBoot에서 addCorsMappings 메소드를 오버라이딩하고,  
http://localhost:3000에서 오는 요청들을 허용시켜줌.  
-> 지금은 리액트를 거둬내어 발생하지 않음


### Windows 환경의 mustache userName 변수로 값을 넘길 때 생기는 오류
로그인 시 OAuth2Attributes 값과 SessionUser의 값도 이상이 없었고 컨트롤러에서 모델에 담는 값에도 이상이 없었음.  
구글링 해보니 Windows OS 사용시 userName 으로 값을 주고받으면 윈도우 사용자명이 출력된다고 함. 예약어인듯 합니다.  

---

## InteliJ IDE 관련

### Build cancelled while executing task ':Application.main()'
커뮤니티 버전으로 바꾼 후 IDE 설정을 다시 했는데 안된 것 중  
Settings -> Gradle -> 우측 하단의 Build and run using, Run tests using을 모두 InteliJ로 바꿔줌  