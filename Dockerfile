FROM openjdk:17
LABEL authors="muhammadreza"

COPY target/demo-0.0.1-SNAPSHOT.jar app-1.0.0.jar

ENTRYPOINT [ "java", "-jar", "app-1.0.0.jar" ]