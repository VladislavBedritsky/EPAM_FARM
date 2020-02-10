
FROM openjdk:latest
COPY target/EPAM_FARM-1.5.war EPAM_FARM-1.5.war
CMD ["java","-jar","EPAM_FARM-1.5.war"]