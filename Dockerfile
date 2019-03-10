FROM java
ADD ./service/target/generator-jar-with-dependencies.jar /
ENTRYPOINT ["java", "-jar", "/generator-jar-with-dependencies.jar"]
