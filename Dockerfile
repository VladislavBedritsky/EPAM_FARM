
FROM openjdk:latest
COPY target/EPAM_FARM-1.0-SNAPSHOT.war EPAM_FARM-1.0-SNAPSHOT.war
CMD ["java","-jar","EPAM_FARM-1.0-SNAPSHOT.war"]