FROM java
ADD . //
ENTRYPOINT ["java", "-jar", "/generator-jar-with-dependencies.jar"]
