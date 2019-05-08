FROM openjdk:8-jre
COPY target/generator-jar-with-dependencies.jar /app/
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]

