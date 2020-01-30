# Opitz Consulting Spring Boot Demo

Copy of the backend provided by Michael Schmidt (https://github.com/mschmidt3/springboot_demo/)

## getting started

### mit maven

```
./mvnw package
./mvnw clean
```

### mit gardle

```
./gradlew bootJar
./gradlew clean
```

# Steps

- step_0 - app created
- step_1 - HelloWorldController returns Hello World
- step_2 - HelloWorldController with multiple methods
- setp_3 - JPA

# Countries

```
# Entity anlegen
mkdir .\src\main\java\de\opitz\consulting\example\demo\entities
touch .\src\main\java\de\opitz\consulting\example\demo\entities\Countries.java

# repository anlegen
mkdir .\src\main\java\de\opitz\consulting\example\demo\repositories
touch .\src\main\java\de\opitz\consulting\example\demo\repositories\CountreisRepository.java

# Controller anlegen
touch .\src\main\java\de\opitz\consulting\example\demo\controllers\CountriesController.java

# jetzt fehlen Daten
touch .\src\main\java\de\opitz\consulting\example\demo\CountriesDataLoader.java
```

```
# CReate
curl -X POST -H "Content-Type: application/json" http://localhost:8080/api/countries/ -d '{"name":"Spanien","alpha2code":"ES","capital":"Madrid"}'

# Update
curl -X PUT -H "Content-Type: application/json" http://localhost:8080/api/countries/4 -d '{"id":4,"name":"Spanien","alpha2code":"ES","alpha3code":"ESP","capital":"Madrid","region":null,"subregion":null,"population":null}'

# Delete
curl -X DELETE http://localhost:8080/api/countries/4
```
