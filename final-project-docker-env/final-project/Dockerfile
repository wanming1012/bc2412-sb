FROM maven:latest AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
LABEL maintainer="wanming1012@gmail.com"
COPY --from=build /app/target/final-project-0.0.1-SNAPSHOT.jar /final-project-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/final-project-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080