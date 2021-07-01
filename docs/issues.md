# L.E. Assemble Issues

## CORS 관련
localhost:3000(리액트)에서 localhost:8080(스프링부트 API서버)에 api를 호출할 때 CORS 에러 발생  
리액트에서 createProxyMiddleware 설정, SpringBoot에서 addCorsMappings 메소드를 오버라이딩하고,  
http://localhost:3000에서 오는 요청들을 허용시켜줌.

