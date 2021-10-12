# L.E. Assemble Issues

## 구현 시 겪은 Issue

### CORS Issue
localhost:3000(리액트)에서 localhost:8080(스프링부트 API서버)에 api를 호출할 때 CORS 에러 발생  
리액트에서 createProxyMiddleware 설정, SpringBoot에서 addCorsMappings 메소드를 오버라이딩하고,  
http://localhost:3000에서 오는 요청들을 허용시켜줌.   
지금은 리액트를 거둬내어 발생하지 않음


##### Windows 환경의 mustache userName 변수로 값을 넘길 때 생기는 오류
로그인 시 OAuth2Attributes 값과 SessionUser의 값도 이상이 없었고 컨트롤러에서 모델에 담는 값에도 이상이 없었음.  
구글링 해보니 Windows OS 사용시 userName 으로 값을 주고받으면 윈도우 사용자명이 출력된다고 함. 예약어인듯 합니다.  

##### Build cancelled while executing task ':Application.main()'
커뮤니티 버전으로 바꾼 후 IDE 설정을 다시 했는데 안된 것 중  
Settings -> Gradle -> 우측 하단의 Build and run using, Run tests using을 모두 InteliJ로 바꿔줌  

##### Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'storeService' defined in file [C:\Users\SEUNGHOON\Web Project\leassemble-back\out\production\classes\com\hoondragonite\leassemble\service\StoreService.class]: Unsatisfied dependency expressed through constructor parameter 0; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'storeRepository' defined in com.hoondragonite.leassemble.domain.store.StoreRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Invocation of init method failed; nested exception is org.springframework.data.repository.query.QueryCreationException: Could not create query for public abstract java.util.List com.hoondragonite.leassemble.domain.store.StoreRepository.findAllByUser_ID(java.lang.Long)! Reason: Failed to create query for method public abstract java.util.List com.hoondragonite.leassemble.domain.store.StoreRepository.findAllByUser_ID(java.lang.Long)! No property user found for type Store!; nested exception is java.lang.IllegalArgumentException: Failed to create query for method public abstract java.util.List com.hoondragonite.leassemble.domain.store.StoreRepository.findAllByUser_ID(java.lang.Long)! No property user found for type Store!
findBySomething : Something은 멤버변수로 존재하는 그대로 적음(컬럼명 아님)  

##### Spring Security 적용 후 JUnit5 테스트 302코드 반환
권한 문제로 @WithMockUser(roles = "USER") 사용

##### com.fasterxml.jackson.databind.JsonMappingException: Infinite recursion (StackOverflowError)
양방향 매핑 오류
