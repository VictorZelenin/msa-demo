FROM alpine:edge

RUN apk add --no-cache openjdk8

EXPOSE 8085

COPY target/msa-encryptionservice-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]