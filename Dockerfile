FROM adoptopenjdk/openjdk11:alpine
EXPOSE 80
ARG JAR_FILE=target/auth-user-0.0.2-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]