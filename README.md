# Meeting Room Reservation System

This is a meeting room reservation system built with Spring Boot.

## Architecture

This project built using Spring Boot, which provides a framework for building stand-alone, production-grade Spring-based applications.

The project is organized into several modules:

- `spring-boot-starter-web` for building web, including RESTful, applications using Spring MVC. It uses Tomcat as the default embedded container.
- `spring-boot-starter-security` for Spring Security features.
- `mybatis-plus-boot-starter` for integrating MyBatis plus with Spring Boot.
- `jjwt` for JSON Web Token support.
- `mysql-connector-java` for MySQL JDBC driver.
- `lombok` to reduce boilerplate code.
- `slf4j-api` and `slf4j-log4j12` for logging.
- `guava` for core libraries for Java-based projects.
- `fastjson` for fast JSON processing.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

#### clone project
```sh
git clone git@github.com:Z-an-K/booking-system.git
```
#### change diretory
```sh
cd booking-system
```
#### init database
change username password and database to yours
```sh
mysql -u [username] -p [password] [database] < db.sql
```
### Installing
A step by step series of examples that tell you how to get a development environment running.
#### configuration
change to your configuration
```sh
cd src/main/resources/application.yaml
```
### Test
Explain how to run the automated tests for this system.
```sh
mvn test
```
### Deployment
Add additional notes about how to deploy this on a docker.
```docker
docker pull <image-name>:<tag>
docker-compose up
```

