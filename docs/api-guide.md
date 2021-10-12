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
GET /api/user/{id}  
PUT /api/user/{id}

### store
GET : /api/user/{id}/stores  
GET : /api/user/{id}/stores/{id}  
POST : /api/user/{id}/stores  
PUT /api/user/{id}/stores/{store_id}  
DELETE /api/user/{id}/stores/{store_id}

### product
GET : /api/user/{id}/store/{id}/products  
GET : /api/user/{id}/store/{id}/products/{id}  
POST : /api/user/{id}/store/{id}/products  
PUT : /api/user/{id}/store/{id}/products/{id}  
DELETE : /api/user/{id}/store/{id}/products/{id}

### store event
GET : /api/user/{id}/store/{id}/events  
GET : /api/user/{id}/store/{id}/events/{id}  
POST : /api/user/{id}/store/{id}/events  
PUT : /api/user/{id}/store/{id}/events/{id}  
DELETE : /api/user/{id}/store/{id}/events/{id}

### store event item
GET : /api/user/{id}/store/{id}/events/{id}/items  
GET : /api/user/{id}/store/{id}/events/{id}/items/{id}  
POST : /api/user/{id}/store/{id}/events/{id}/items  
PUT : /api/user/{id}/store/{id}/events/{id}/items/{id}  
DELETE : /api/user/{id}/store/{id}/events/{id}/items/{id}

### order
GET : /api/users/{id}/orders
GET : /api/users/{id}/orders/{id}
POST : /api/users/{id}/orders
DELETE : /api/users/{id}/orders/{id}

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