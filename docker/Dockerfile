FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD simple-migration-service-1.0-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java", "-jar", "/app.jar"]