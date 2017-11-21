Customer Service
--

Customer Service : Get customer details


## Build
To generate ASCII doc

```sh
mvn package 
```

## To Run
```sh
mvn spring-boot:run

debug
mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8008"

Run with profile

java -jar -Dspring.profiles.active=dev  target/customer-service-0.0.1-SNAPSHOT.jar

```
