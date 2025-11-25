# jwt-apigateway-security

## Regist an user

```
curl --location --request POST 'http://localhost:8080/auth/register' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=7CE91EE75A65277C0DCB6C5736C5DF5D' \
--data-raw '{
    "name":"vivek",
    "password":"Pwd1",
    "email":"vivek@gmail.com"
}'

```

## Generate token

```
curl --location --request POST 'http://localhost:9898/auth/token' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=7CE91EE75A65277C0DCB6C5736C5DF5D' \
--data-raw '{
    "username":"vivek",
    "password":"Pwd1"
}'
```
## Access Swiggy-app

```
curl --location --request GET 'http://localhost:8080/swiggy/37jbd832' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2aXZlayIsImlhdCI6MTc2NDA3MjAyNCwiZXhwIjoxNzY0MDczODI0fQ.kP2SmIz9sKiJ6JmC3ZSz4CIMk3MrzRNpVRBko0mrxZs' \
--header 'Cookie: JSESSIONID=7CE91EE75A65277C0DCB6C5736C5DF5D'
```

## Access Restaurant-service

```
curl --location --request GET 'http://localhost:8080/restaurant/orders/status/37jbd832' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJCYXNhbnQiLCJpYXQiOjE2NzkwNTU1MDcsImV4cCI6MTY3OTA1NzMwN30.9nNAW1rx8RoTIrhn5Abtzg7RplvT9_d-U5EOwUcJZq8' \
--header 'Cookie: JSESSIONID=7CE91EE75A65277C0DCB6C5736C5DF5D'
```
