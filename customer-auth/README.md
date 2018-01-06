# Customer Auth Service

Authentication and Authorization Service .

 - Spring Boot
 - Spring Cloud
 - Lombok   

 TODO : Reused existing code , have to implement OAth2
 
 ```sh
 # First time to load the data
 # set INIT_DB property to true , default false
 java -jar -DINIT_DB=true customer-auth-0.0.1-SNAPSHOT.jar
 ```
 
### Login
```
 $ curl 'http://localhost:8070/v1.0/auth' -i -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{ "username": "appuser", "password": "appuser"}'
 
 Response
 {
    "token": "51ace996-f34f-426f-babd-fc3cf2acae33",
    "username": "appuser",
    "sessionStarted": "2018-01-06T13:41:46.124",
    "activeTime": "2018-01-06T13:44:17.942"
}

 ```
 
### Validate

```
$ curl 'http://localhost:8070/v1.0/auth' -i -X GET -H 'Content-Type: application/json' -H 'Accept: application/json' -H 'x-auth-token:51ace996-f34f-426f-babd-fc3cf2acae33'

```