FROM openjdk:8-alpine
RUN mkdir -p /usr/src/myapp
COPY application.properties /usr/src/myapp
COPY target/powerboost-0.0.1-SNAPSHOT.jar /usr/src/myapp
WORKDIR /usr/src/myapp
CMD ["java", "-jar", "powerboost-0.0.1-SNAPSHOT.jar", "-Dspring.config.location=", "."]
