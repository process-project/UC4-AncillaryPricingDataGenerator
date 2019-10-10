FROM openjdk:8-jre

RUN apt-get update --fix-missing && apt upgrade -y && DEBIAN_FRONTEND=noninteractive apt-get install -y -q dos2unix && apt-get clean

COPY target/generator-jar-with-dependencies.jar /app/
COPY entrypoint.sh /entrypoint.sh

RUN dos2unix /entrypoint.sh
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]

