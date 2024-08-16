# syntax=docker/dockerfile:1

# MAVEN: application
FROM maven:3.9.6-amazoncorretto-21 AS builder
WORKDIR /seeder
COPY . .
RUN mvn install -DskipTests

#
# RUN THE APPLICATION
#
FROM amazoncorretto:21-alpine-jdk
WORKDIR /seeder
COPY --from=builder /seeder/target/*.jar ldes-member-seeder.jar

CMD ["java", "-jar", "ldes-member-seeder.jar"]