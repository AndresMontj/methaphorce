FROM openjdk:22
WORKDIR /opt
VOLUME /tmp
ENV PORT 8082
EXPOSE 8082
COPY target/Metaphorce-0.0.1-SNAPSHOT.jar service.jar
ENTRYPOINT ["java", "-jar","service.jar"]
