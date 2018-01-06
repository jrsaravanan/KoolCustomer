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

## Run Container in local
```sh

$ mvn dockerfile:build

$ export CUSTOMER_SVR_HOST_NAME=$(hostname  -I | cut -f1 -d " ")

$ docker run -d --name mysql-server -e MYSQL_DATABASE="auth" -e MYSQL_USER="appuser" -e MYSQL_PASSWORD="appuser"  -e MYSQL_ROOT_PASSWORD="appuser" -e MYSQL_ROOT_HOST=$CUSTOMER_SVR_HOST_NAME -p 3306:3306 mysql:5.6

# First Time enable  INIT_DB=true
$ docker run  -e INIT_DB=true -e AUTH_APP_USER='appuser' -e AUTH_APP_PASSWORD='appuser'  -e AUTH_DB_URI=$CUSTOMER_SVR_HOST_NAME:3306/auth   -p 8070:8070 -t jrsaravanan/customer-auth

```