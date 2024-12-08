# Alap Docker image
FROM openjdk:17-jdk-slim

# Másoljuk be a JAR fájlt a Docker image-be
COPY target/ExamApp-0.0.1-SNAPSHOT.jar app.jar

# Az alkalmazás elindítása
ENTRYPOINT ["java", "-jar", "app.jar"]
