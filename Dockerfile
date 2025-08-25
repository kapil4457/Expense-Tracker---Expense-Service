
FROM amazoncorretto:21
COPY ./build/libs/service-0.0.1-SNAPSHOT.jar ./expense-service.jar
ENTRYPOINT ["java","-jar","expense-service.jar"]
