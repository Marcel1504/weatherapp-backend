FROM eclipse-temurin:20-jre-alpine
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]