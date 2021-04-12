FROM openjdk:8

RUN useradd -m -d /home/service service

COPY --chown=service:service build/libs/complete-0.0.1-SNAPSHOT.jar  /home/service/app.jar

user service

WORKDIR /home/service

cmd ["java", "-jar", "app.jar"]