FROM openjdk:8-alpine
RUN mkdir -p /usr/src/myapp
COPY application.properties /usr/src/myapp
COPY target/ultraboost-0.0.1-SNAPSHOT.jar /usr/src/myapp
WORKDIR /usr/src/myapp
CMD ["java", "-jar", "ultraboost-0.0.1-SNAPSHOT.jar", "-Dspring.config.location=", "."]
