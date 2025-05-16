FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY erudite-server-all.jar /app/app.jar

COPY application.yaml /app/application.yaml

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]