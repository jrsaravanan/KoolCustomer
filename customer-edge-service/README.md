# Customer Edge Service 
This provides API gateway capabilities for micro-services using Netflix Zuul. 

This service has authentication filter enabled , It check for authentication token in every request.

## To Run in local 
```

# Start Customer Service
# Start Auth Service
# Start Discovery , Before starting edge service

java -jar target\customer-edge-service-0.0.1-SNAPSHOT.jar

```


## Login
```
POST /v1.0/auth/login HTTP/1.1
Host: localhost:9090
Accept: application/json
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: a5f29bff-fe6c-a199-467e-4ad09410da57

{
	"username" :"appuser",
	"password" : "appuser"
}
```

```
 $ curl 'http://localhost:9090/v1.0/auth/login' -i -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{ "username": "appuser", "password": "appuser"}'
 
 Response
 {
    "token": "51ace996-f34f-426f-babd-fc3cf2acae33",
    "username": "appuser",
    "sessionStarted": "2018-01-06T13:41:46.124",
    "activeTime": "2018-01-06T13:44:17.942"
}

```
 
## Invoke Customer Service
```
curl 'http://localhost:9090/v1.0/customers/ping' -i -X GET -H 'Content-Type: application/json' -H 'Accept: application/json' -H 'x-auth-token:51ace996-f34f-426f-babd-fc3cf2acae3
```