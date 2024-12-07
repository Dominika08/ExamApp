# Alapként használjuk az OpenJDK 17-et
FROM openjdk:17-jdk-slim

# Másoljuk be a buildelt JAR fájlt a Docker image-be
COPY target/examapp.jar examapp.jar

# Az alkalmazás elindítása a Docker konténerben
ENTRYPOINT ["java", "-jar", "examapp.jar"]
