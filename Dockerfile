FROM openjdk:11
MAINTAINER ramirosanchezl
ARG JAR_FILE=target/challenge-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} challenge.jar
ENTRYPOINT ["java","-jar","/challenge.jar"]