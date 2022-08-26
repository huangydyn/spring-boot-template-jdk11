# base image
FROM openjdk:11
# add package to image
ADD build/libs/spring-boot-template-jdk11-1.0.jar /app/
# work directory
WORKDIR /app
# entrypoint
CMD ["java", "-jar", "spring-boot-template-jdk11-1.0.jar"]
