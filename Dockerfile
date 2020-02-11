
FROM openjdk:latest
COPY target/EPAM_FARM-1.6.war EPAM_FARM-1.6.war
CMD ["java","-jar","EPAM_FARM-1.6.war"]