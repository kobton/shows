# Search SR radio shows information 

Spring boot application to fetch information about SR radio shows. The Spring Boot API will return latest episodes of shows with title, description and published date. To make show retrieval more efficient, client requests must specify the channel in the api path P1, P2 or P3. In order to not exhaust the external SR API, only three concurrent requests is allowed for this application. 

### Application structure and packages

- Controller: REST controller defining the endpoints
- Data: model classes for external responses and client response
- Exception: custom exception handling for show not found and data limit
- Service: service layer to manage application logic
- Tests: tests written in JUnit 5

### Run application

This is a Spring Boot application built with Maven and Kotlin.

The application can be run with Maven using:

```
mvn clean install
mvn spring-boot:run
```

The application can also be run with docker compose:

Build application:

```
mvn clean install
mvn clean package
```

Run application with docker compose

```
docker compose up --build

docker compose kill -s SIGINT
```

### Sample requests

Get show info:

```
curl --request GET \
  --url 'http://localhost:8080/api/p1?show=studio%20ett' 
```

Response: 

```
{
	"name": "Studio Ett",
	"episodeList": [
		{
			"title": "Studio Ett",
			"description": "Direktsänt aktuellt magasin. ",
			"publishdateutc": "2024-12-09"
		},
		{
			"title": "Studio Ett",
			"description": "Fördjupar dagens stora händelser i Sverige och världen.",
			"publishdateutc": "2024-12-09"
		},
	.....
```
### Notes and possible improvements

- Better search algorithm for finding shows
- Controller tests and integration tests
- Add logging
- Add support for all P4 channels
- Because of issues with deserializing Kotlin data classes I used Java classes for the data modelling [link to issue](https://github.com/FasterXML/jackson-module-kotlin/issues/153)
