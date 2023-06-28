FROM openjdk:17
EXPOSE 9003
ADD target/retail-prod-docker.jar retail-prod-docker.jar 
ENTRYPOINT ["java", "-jar","/retail-prod-docker.jar"]