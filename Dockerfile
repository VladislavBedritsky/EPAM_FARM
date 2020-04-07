FROM sonarqube:7.9-community
COPY ./config/sonarqube/data/sonar.h2.db: /opt/sonarqube/data/sonar.h2.db