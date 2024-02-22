FROM amazoncorretto:17
EXPOSE 9176
ADD target/my-hr-0.0.1.jar my-hr-0.0.1.jar
ENTRYPOINT ["java","-jar","my-hr-0.0.1.jar"]