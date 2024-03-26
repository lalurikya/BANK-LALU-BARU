FROM maven:3.8.1-openjdk-17-slim AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
COPY . $HOME
RUN mvn -Dmaven.test.skip=true clean package spring-boot:repackage

FROM openjdk:20-ea-17-slim-buster
WORKDIR /usr/app
COPY --from=build /usr/app/target/*.jar /usr/app/*.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/*.jar"]