# L.E. Assemble REST API Guide

## user

GET /api/user/{id}
POST /api/user/{id}
PUT /api/user/{id}
DELETE /api/user/{id}

## store
GET : /api/owner/{user_id}/stores
GET : /api/owner/{user_id}/stores/{store_id}
POST : /api/owner/{user_id}/stores
PUT /api/owner/{user_id}/stores/{store_id}
DELETE /api/owner/{user_id}/stores/{store_id}

## product
GET : /api/store/{store_id}/products
GET : /api/store/{store_id}/products/{product_id}
POST : /api/store/{store_id}/products
PUT : /api/store/{store_id}/products/{product_id}
DELETE : /api/store/{store_id}/products/{product_id}

## store event
GET : /api/store/{store_id}/events
GET : /api/store/{store_id}/events/{event_id}
POST : /api/store/{store_id}/events
PUT : /api/store/{store_id}/events/{event_id}
DELETE : /api/store/{store_id}/events/{event_id}

## store event product
GET : /api/store/{store_id}/events/{event_id}/products
GET : /api/store/{store_id}/events/{event_id}/products/{product_id}
POST : /api/store/{store_id}/events/{event_id}/products
PUT : /api/store/{store_id}/events/{event_id}/products/{product_id}
DELETE : /api/store/{store_id}/events/{event_id}/products/{product_id}

## order
GET : /api/users/{user_id}/orders
GET : /api/users/{user_id}/orders/{order_id}
POST : /api/users/{user_id}/orders
DELETE : /api/users/{user_id}/orders/{order_id}

## login
/oauth2/callback/{서비스코드}
/oauth2/callback/google