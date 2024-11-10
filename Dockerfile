FROM eclipse-temurin:21 AS builder
WORKDIR /src
ADD . /src
RUN ./gradlew clean build -x test

FROM eclipse-temurin:21

WORKDIR /app
COPY --from=builder /src/build/*.jar /app/app.jar
ENV JAVA_OPTIONS=-XX:+UseContainerSupport
ENTRYPOINT [ "/bin/sh", "-c", "java -jar /app/app.jar" ]
EXPOSE 8080