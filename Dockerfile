FROM openjdk:11
EXPOSE 9090
ADD target/user-service-docker.war user-service-docker.war
ENTRYPOINT ["java","-jar","user-service-docker.war"]