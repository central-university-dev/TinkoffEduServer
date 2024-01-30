FROM openjdk:17-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} edu-backend.jar

ENTRYPOINT ["java","-jar","-XX:+UseSerialGC","-Xss512k","-XX:MaxRAM=256m","/sd-hw2.jar"]

EXPOSE 8000
