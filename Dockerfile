
FROM openjdk:latest
COPY ./target/EPAM_FARM-1.0-SNAPSHOT.jar EPAM_FARM-1.0-SNAPSHOT.jar
CMD ["java","-jar","EPAM_FARM-1.0-SNAPSHOT.jar"]