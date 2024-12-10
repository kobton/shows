FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/shows-0.0.1-SNAPSHOT.jar /app/shows.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "shows.jar"]