[![Build Status](https://travis-ci.org/jrsaravanan/KoolCustomer.svg?branch=master)](https://travis-ci.org/jrsaravanan/KoolCustomer)

# KoolCustomer

A Microservice PoC. 

Build Using
* JDK 8
* Spring Boot
* Spring Admin
* Eureka
* Admin UI
* Cucummber JVM
* Rest Assured
* REST Doc

## Build 
```
$ .\mvn.install.sh
$ docker-compose up
```

## 

[[overview-http-verbs]]
== HTTP verbs
Customer Service uses REST standards and adhere HTTP verbs for opearion.

|===
| Verb | Usage

| `GET`
| Used to retrieve a resource

| `POST`
| Used to create a new resource

| `PATCH`
| Used to update an existing resource, including partial updates

| `PUT`
| Used to update an existing resource, full updates only

| `DELETE`
| Used to delete an existing resource
|===

[[overview-http-status-codes]]
== HTTP status codes
The following status codes are used in customer service. As per standard HTTP/REST convention

|===
| Status code | Usage

| `200 OK`
| Standard response for successful HTTP requests.
In a GET request, the response will contain an entity corresponding to the requested resource.
In a POST request, the response will contain an entity describing or containing the result of the action.

| `201 Created`
| The request has been fulfilled and resulted in a new resource being created.

| `204 No Content`
| The server successfully processed the request, but is not returning any content.

| `400 Bad Request`
| The server cannot or will not process the request due to something that is perceived to be a client error (e.g., malformed request syntax, invalid request message framing, or deceptive request routing).

| `404 Not Found`
| The requested resource could not be found but may be available again in the future. Subsequent requests by the client are permissible.
|===
