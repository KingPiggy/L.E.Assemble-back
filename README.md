# Limited Edition Assemble
 
- 한정판매상품만 취급하는 쇼핑몰 프로젝트
- Spring Boot, JPA, Test, AWS Infra, CI/CD 학습

## 진행상황
**Done**
1. JPA를 사용한 일대일, 일대다 관계의 조회/저장/수정/삭제 구현
2. RESTful API 설계
3. MockMvc를 이용한 Controller, Service, Repository 에 대한 단위 테스트 코드 작성(라우팅, 기능구현)
4. AWS 인프라 구성 
5. CI/CD 구성 (Travis CI, AWS CodeDeploy, AWS S3, Nginx)
6. OAuth2를 이용한 소셜로그인(구글, 카카오)

**To Do**
1. 이벤트 상품 구매 관련 로직, 화면(재고확인 - 주문내역 생성 - 재고차감)
2. 분산처리 구현해보기
3. 이벤트 상품 등록, 이벤트 찾기, 구매관련 화면

## Article
1. [다대일 연관관계 저장](https://kingpiggylab.tistory.com/329)  
2. [OAuth2 소셜로그인](https://kingpiggylab.tistory.com/323)
3. [2대의 로드밸런서 적용, 배포 시나리오 수정](https://kingpiggylab.tistory.com/324)

## 주요 코드
1. [Store API](https://github.com/KingPiggy/L.E.Assemble-back/blob/master/src/main/java/com/hoondragonite/leassemble/web/StoreApiController.java)
2. [MockMvc를 이용한 Unit Test](https://github.com/KingPiggy/L.E.Assemble-back/blob/master/src/test/java/com/hoondragonite/leassemble/web/StoreApiControllerTest.java)

## 프로젝트 구조
![프로젝트 구조](/assets/project_structure.png)

## 기술스택

|구분|내용|
|---|---|
|Client|Mustache|
|API Server|Spring Boot 2.5.0|
|DB|AWS RDS(MariaDB), AWS ElasticSearch(Redis Cache)|
|Build|Gradle|
|CI/CD|Github, Travis CI, AWS S3, AWS CodeDeploy, Shell Script|
|Infra|AWS EC2, nginx(load balancing, reverse proxy)|
|Storage|AWS S3|
|Test|JUnit5, NGrinder(아직 미사용)|
|ETC|Kakao Oven, ERWin|

## ERD
그림 추가
초안 ERD입니다.

## 프로젝트 팀원
KingPiggy

<!--
## Issues
그림 추가

## 성능 테스트
그림 추가

## 추가 개발사항
그림 추가
-->
