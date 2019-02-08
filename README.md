# Simple Spring Boot, Maven, Kotlin, MySQL + Oauth 2.0 demo

This project is a spring boot kotlin Rest API with Oauth2.0.

### Stack
- Kotlin
- Spring Data JPA 
- Spring Boot
- MySQL
- Oauth 2.0

### Prerequisites
- JDK 1.8 or later
- Maven 3 or later
- MySQL 5 or later

### Installation

**1. Exceute following SQL statement for creating MySQL database before launching the app.** 

```sql
CREATE DATABASE IF NOT EXISTS kotlin_api_demo;
```

**2. Change MySQL username and password**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password`

### Run
```bash
mvn spring-boot:run
```
**1. Exceute SQL script given in the below file to create oauth table and add user and client details.** 

+ open `config/Ouath_schema.sql`

+ Now everything is up you can execute the token request and access the resources based on that access token. Below is the postman collection to execute the request.

+ open `Rest API With Oauth.postman_collection.json`
