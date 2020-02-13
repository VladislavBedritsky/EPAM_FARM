#              jenkins

FROM jenkins/jenkins:lts
USER root
RUN apt update && \
    apt install -y maven

COPY config/jenkins_home /var/jenkins_home

