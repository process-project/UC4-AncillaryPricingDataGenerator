FROM openjdk:8-jre
MAINTAINER lhsystems
COPY maven /app/
ENTRYPOINT java -Xmx1024m $JVM_ARGS -jar /app/data-generator-package-1.0-SNAPSHOT.jar
