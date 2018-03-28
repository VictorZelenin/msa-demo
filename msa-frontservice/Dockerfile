FROM alpine:edge

RUN apk add --no-cache openjdk8

EXPOSE 8084

COPY target/msa-frontservice-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]