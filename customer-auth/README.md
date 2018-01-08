# Customer Auth Service

Authentication and Authorization Service .
Simple login service , just a plain db authentication
TODO : Reused existing code , have to implement OAth2

 - Spring Boot
 - Spring Cloud , Eureka
 - Lombok   
 - Spring Admin
 
 ```sh
 # First time to load the data
 # set INIT_DB property to true , default false
 java -jar -DINIT_DB=true customer-auth-0.0.1-SNAPSHOT.jar
 ```
 
### Login
```
 $ curl 'http://localhost:8070/v1.0/auth/login' -i -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{ "username": "appuser", "password": "appuser"}'
 
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

## Run Container in local
```sh

$ mvn dockerfile:build


$ docker run -d --name db -e MYSQL_DATABASE="auth" -e MYSQL_USER="appuser" -e MYSQL_PASSWORD="appuser"  -e MYSQL_ROOT_PASSWORD="root" -p 3306:3306 mysql:5.6

# whenever you span a new container enable  INIT_DB=true
$ docker run  -e PORT=8070 -e INIT_DB=true -e AUTH_APP_USER='appuser' -e AUTH_APP_PASSWORD='appuser'  -e AUTH_DB_URI='db:3306/auth'   -p 8070:8070 --name customer-auth  --link db:db -t jrsaravanan/customer-aut
h

```
