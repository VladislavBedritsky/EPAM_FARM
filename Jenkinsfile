pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Connect') {
            steps {
                sh 'docker -H localhost:2375 images'
            }
        }
        stage('Deploy') {
            steps{
                sh 'docker-compose up'
            }
        }
    }
}