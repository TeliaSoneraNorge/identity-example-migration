# Simple user migration service example

## Run
Do either:
* mvn package docker:build && docker run -p 8080:8080 -t simple-migration-service 
* run com.telia.Application from IDE

## Test
curl: curl -v http://localhost:8080/e164/+4781549300

httpie: http --verbose http://localhost:8080/e164/+4781549300 Accept:application/json
  
### Example
#### Happy
##### Request
```
GET /e164/+4781549300 HTTP/1.1
Accept: application/json
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: localhost:8080
User-Agent: HTTPie/0.9.2
```

##### Response
```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Date: Wed, 24 Aug 2016 12:57:35 GMT
Transfer-Encoding: chunked
```
```json
{
    "issuer_id": "issuerId", 
    "user": {
        "address": null, 
        "birthdate": null, 
        "email": null, 
        "external_id": "123", 
        "family_name": "Nameson", 
        "gender": null, 
        "given_name": "Name", 
        "middle_name": null, 
        "nickname": null, 
        "phone_number": "+4781549300", 
        "picture": null, 
        "preferred_locale": null, 
        "preferred_username": null, 
        "profile": null, 
        "website": null
    }
}
```

#### Bad :(
##### Request
```
GET /e164/NOT_FOUND HTTP/1.1
Accept: application/json
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: localhost:8080
User-Agent: HTTPie/0.9.2
```

##### Response
```
HTTP/1.1 404 
Content-Type: application/json;charset=UTF-8
Date: Thu, 25 Aug 2016 08:32:13 GMT
Transfer-Encoding: chunked
```
```json
{
    "error": "Something is wrong, can't find user: NOT_FOUND"
}
```