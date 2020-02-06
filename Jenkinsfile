pipeline {
    agent any
    stages {
        stage('SonarQube Analysis') {
            withSonarQubeEnv('Sonarqube') {
                sh 'mvn clean package sonar:sonar'
            }
        }
    }
}
