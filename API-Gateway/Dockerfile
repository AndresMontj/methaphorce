FROM openjdk:22
WORKDIR /opt
VOLUME /tmp
ENV PORT 8090
EXPOSE 8090
COPY target/API-Gateway-0.0.1-SNAPSHOT.jar apiservice.jar
ENTRYPOINT ["java", "-jar","apiservice.jar"]
