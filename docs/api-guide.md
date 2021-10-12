# L.E. Assemble API Guide

## HTTP 메소드별 어노테이션 규칙
|기능|구분|어노테이션|
|---|---|--------|
|생성|POST|@PostMapping("/api/something")|
|목록조회|GET|@GetMapping("/api/something")|
|단건조회|GET|@GetMapping("/api/something/{id}")|
|수정|PUT|@PutMapping("/api/something/{id}")|
|삭제|DELETE|@DeleteMapping("/api/something/{id}")|

---

## 사용자 관련 API
- 접근권한 : 사용자 본인

### user
GET /api/user  
PUT /api/user

### store
GET : /api/user/stores  
GET : /api/user/stores/{id}  
POST : /api/user/stores  
PUT /api/user/stores/{store_id}  
DELETE /api/user/stores/{store_id}

### product
GET : /api/user/stores/{id}/products  
GET : /api/user/stores/{id}/products/{id}  
POST : /api/user/stores/{id}/products  
PUT : /api/user/stores/{id}/products/{id}  
DELETE : /api/user/stores/{id}/products/{id}

### store event
GET : /api/user/stores/{id}/events  
GET : /api/user/stores/{id}/events/{id}  
POST : /api/user/stores/{id}/events  
PUT : /api/user/stores/{id}/events/{id}  
DELETE : /api/user/stores/{id}/events/{id}  

### store event item
GET : /api/user/stores/{id}/events  
GET : /api/user/stores/{id}/events/{id}  
POST : /api/user/stores/{id}/events  
PUT : /api/user/stores/{id}/events/{id}  
DELETE : /api/user/stores/{id}/events/{id}

### order
GET : /api/user/{id}/orders
GET : /api/user/{id}/orders/{id}
POST : /api/user/{id}/orders
DELETE : /api/user/{id}/orders/{id}

---

## 기능별 API

### 진행중 이벤트
GET : /store-events  
GET : /store-events/{api}

---

### login
/oauth2/callback/{서비스코드}
/oauth2/callback/google
/oauth2/callback/kakao