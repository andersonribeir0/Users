FROM openjdk:8
ADD target/users.jar users.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","users.jar"]