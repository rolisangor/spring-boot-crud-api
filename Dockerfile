ENV JDK_VERSION=openjdk:17-jdk-alpine
FROM ${JDK_VESRION}
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]