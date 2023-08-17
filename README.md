# Spring Boot Project (JDBC)
### Project for Examen Final PROG2
This repository contain the project who managening absence in school.
For create web server (REST API) using JDBC to connect to database.
<br/>

## MCD

![image_mcd](database.png)

<br/>
<hr/>

### Require
This project use and you need if you would like clone them :

-   Java version 17.0 or higher
-   PostgreSQL 14 or higher
-   Apache Maven 1.8 or higher

### Installation
Befor all, you should need execute `data.sql`  ( in ``/Server/src/main/resources/data.sql``)

If you have a IDE, just run this app on Server class `Server.java` and in your environment vairable add this configuration :
```js
DB_URL = jdbc:postgresql://localhost:5432/[database_name]
DB_USERNAME = [user_name_of_postgresql]
DB_PASSWORD = [password_of_postgresql]
```
Or run app on directory ``Server`` execute command following :

```sh
$ cd Server
$ mvn install
$ mvn spring-boot:run
```
