FROM openjdk:8-jdk-alpine AS builder
COPY . /bms
WORKDIR /bms
RUN ./mvnw clean install

FROM openjdk:8-jdk-alpine
WORKDIR /bms
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY --from=builder ${JAR_FILE}/app.jar  ./app.jar
ENTRYPOINT ["java","-jar","/app.jar"]