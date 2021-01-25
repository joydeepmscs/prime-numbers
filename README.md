# Prime Numbers Project

Prime Numbers is a Spring Boot based project with a RESTful Prime Numbers API service. 

## How to build

This is a standard Maven project, so just run `mvn clean install` and the project will be compiled and the tests will run.

You require the following to build the project:

* [Oracle JDK 8](http://www.oracle.com/technetwork/java/)
* [Apache Maven](http://maven.apache.org/)

JDK 8 is required to build and run this project.

## Running the Project

The project uses [Spring Boot](http://projects.spring.io/spring-boot/) which makes configuring and running a web based application a breeze.

From the project base directory you can run `mvn spring-boot:run` which will start the application on `http://localhost:8080`

## Using the Project API

The project is configured with two RESTful API operations:

* [Generate Primes](#generate-primes)

### Generate Primes

When called, this API operation will generate prime numbers from `2` to `upperBound` (inclusive).

#### Operation Examples

The example below will return a JSON array of prime numbers from `2` to the given `upperBound`

```
$ curl -v http://localhost:8080/primes?upperBound=100
*   Trying ::1...
* Connected to localhost (::1) port 8080 (#0)
> GET /primes?upperBound=100 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.43.0
> Accept: */*
> 
< HTTP/1.1 200 OK
< Server: Apache-Coyote/1.1
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Date: Sat, 23 Jan 2021 10:15:58 GMT
< 
* Connection #0 to host localhost left intact
[2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97]
```

The example above can be executed again, using the fast algorithm.

```
$ curl -v http://localhost:8080/primes/100?algorithm=2
*   Trying ::1...
* Connected to localhost (::1) port 8080 (#0)
> GET /primes/121?algorithm=2 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.43.0
> Accept: */*
>
< HTTP/1.1 200 OK
< Server: Apache-Coyote/1.1
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Date: Sat, 23 Jan 2021 10:15:58 GMT
< 
* Connection #0 to host localhost left intact
[2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97]
```

Similarly, the example above can be executed again, using the slower algorithm.

```
$ curl -v http://localhost:8080/primes/100?algorithm=3
*   Trying ::1...
* Connected to localhost (::1) port 8080 (#0)
> GET /primes/121?algorithm=3 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.43.0
> Accept: */*
> 
< HTTP/1.1 200 OK
< Server: Apache-Coyote/1.1
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Date: Sat, 23 Jan 2021 10:15:58 GMT
< 
* Connection #0 to host localhost left intact
[2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97]