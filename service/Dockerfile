FROM openjdk:8-jre

COPY target/generator-jar-with-dependencies.jar /app/
COPY entrypoint.sh /
RUN chmod u+x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]

