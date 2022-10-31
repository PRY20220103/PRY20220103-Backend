
FROM openjdk:8
ADD ./target/PRY20220103-Backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]



