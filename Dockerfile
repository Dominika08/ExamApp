# Alap Docker image, ami tartalmazza az OpenJDK 17-et
FROM openjdk:17-jdk-slim

# Másoljuk be a JAR fájlt a Docker image-be
COPY target/examapp.jar examapp.jar

# Az alkalmazás indítása
ENTRYPOINT ["java", "-jar", "examapp.jar"]
