# sprint-security-jwt

This is a kotlin project with spring security and jwt

## Run

``./mvnw spring-boot:run``

## Usage

Use the following endpoint to create a user:

```
curl --location --request POST 'localhost:8080/login/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "userName",
    "password": "userPassword"
}'
```

Use the following endpoint to login and get a jwt token:

```
curl --location --request POST 'localhost:8080/login/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userName": "userName",
    "password": "userPassword"
}'
```

Use the following endpoint to test your access:

```
curl --location --request GET 'localhost:8080/hello/{string}' \
--header 'Authorization: Bearer $token'
```