# Newsletter 

This is a demo of how to write a serverless function using [Spring Cloud Function](https://spring.io/projects/spring-cloud-function).

This application uses: 

- Java 11 (Corretto)
- Spring Boot 2.7.5
- Spring Web (MVC)
- Spring Cloud Function 
- Spring Cloud Function AWS
- Spring Data JDBC
- PostgreSQL
- Testcontainers

## Running the application

If you want to run this application you will need to have Docker Desktop installed. With Docker running you can run
the `docker-compose.yml` in the root of the project to start a PostgreSQL database. 

`docker compose up`

If you look at `application.yml` you will see that the default profile being set is `dev`

```yaml
spring:
  profiles:
    active: dev
```

This will load `application-dev.yml` which configures the database you just started up and sets the init mode to always
so that the DDL script located in `src/main/resources/schema.sql` is executed. 

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/newsletter
    username: postgres
    password: password

  sql:
    init:
      mode: always
```

### Endpoints

When you run the application locally there is a single endpoint at `http://localhost:8080` that will accept a GET request.

REST support to expose functions as HTTP endpoints etc.

## Testing the application

The single test located in `src/test/java/dev/danvega/newseltter/functions/ListSubcribersTest.java` will use [Testcontainers](https://www.testcontainers.org/)
to spin up a PostgreSQL database using Docker. This is done because the test is annotated with `@Profile("test")` which will
load `src/main/resources/application-test.yml`.

```java
@Profile("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ListSubscribersTest {
    // ...
}
```

## Deploy to AWS 

If you want to package this function up to run on AWS you can run the following command: 

`mvn package clean`

In AWS you will need to set the following profile under the function configuration environment variables: 

make sure you set `spring_profiles_active=aws`

You will also need a database on AWS. Once you have the database you will need to configure the following environment variables: 

- DB_HOSTNAME: The hostname of the database server.
- DB_PORT: The port of the database server.
- DB_NAME: The name of the database.
- DB_USERNAME: The username of the credentials used to access the database.
- DB_PASSWORD: The password of the credentials used to access the database.