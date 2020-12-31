# Spring Boot Template(基于Jdk11的SpringBoot模板工程)

This is a template project for Spring Boot based on jdk11, with basic components:

- Spring Boot:2.1.13.RELEASE
- Flyway
- MySQL
- Swagger
- Redis
- JPA
- Logback
- MapStruct
- QueryQSL
- Lombok

Build
- Gradle 5.0

Code Check
- PMD
- CheckStyle
- Jacoco

Spring Cloud
- Spring Cloud:Greenwich.SR1
- Sleuth
- Feign
- OkHttp
- Hystrix

Test
- Junit 5

Deploy
- DockerFile

## How to run MYSQL
```
 docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
```
