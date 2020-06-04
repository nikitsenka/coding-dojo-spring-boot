Spring Boot Coding Dojo
---

Welcome to the Spring Boot Coding Dojo!

### Introduction

This is a simple application that requests its data from [OpenWeather](https://openweathermap.org/) and stores the result in a database.

### Developers 

#### Run locally
Run DB

```docker run -e POSTGRES_PASSWORD=password -e POSTGRES_USER=postgres -e POSTGRES_DB=database -p 5432:5432 -d postgres:12```

Run App

```
 ./mvnw spring-boot:run
```

Run Tests
```
 ./mvnw test
```

### Production readiness plan (deployment and operational readiness out of scope)
 - fix issues and make app runnable
 - add bdd integration tests
 - refactor and add unit tests
 - improve error handling
 - add static code analysis checks
 - add gatling performance tests
 - improve performance and verify
 - add actuator, versioning and build info
 - improve documentation
 - improve logging
 - review
