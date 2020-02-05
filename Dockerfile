
FROM openjdk:latest
COPY target/EPAM_FARM-1.0.war EPAM_FARM-1.0.war
CMD ["java","-jar","EPAM_FARM-1.0.war"]