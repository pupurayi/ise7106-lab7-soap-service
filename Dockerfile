FROM maven:3.8.4-openjdk-11 AS MAVEN_BUILD

MAINTAINER sentrypay.co.zw

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN --mount=type=cache,target=/root/.m2 mvn package

FROM adoptopenjdk:11-jre-hotspot

RUN adduser --system --group sentrypay
USER sentrypay:sentrypay

ARG JAR_FILE=/build/target/*.jar

WORKDIR /app
COPY --from=MAVEN_BUILD ${JAR_FILE} /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]