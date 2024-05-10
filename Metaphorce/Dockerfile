FROM openjdk:22
WORKDIR /opt
VOLUME /tmp
ENV PORT 8082
EXPOSE 8082
COPY target/Metaphorce-0.0.1-SNAPSHOT.jar service.jar
COPY localhost.p12 /ssl/metaphorce/localhost.p12
COPY localhost-cert.pem /etc/pki/ca-trust/source/anchors
RUN update-ca-trust
RUN keytool -import -alias 192.168.3.40 -file /etc/pki/ca-trust/source/anchors/localhost-cert.pem -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit -trustcacerts -noprompt
ENTRYPOINT ["java", "-jar","service.jar"]