# base image
FROM openjdk:11
# add package to image
ADD build/libs/*.jar /app/app.jar
# work directory
WORKDIR /app
# entrypoint
CMD ["java", "-jar", "app.jar"]
