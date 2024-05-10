FROM openjdk:22
WORKDIR /opt
VOLUME /tmp
ENV PORT 8761
EXPOSE 8761
COPY target/Discovery-Server-0.0.1-SNAPSHOT.jar discovery-service.jar
ENTRYPOINT ["java", "-jar","discovery-service.jar"]
