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
                sh 'docker -H localhost:2375'
                sh 'docker ps -a'
            }
        }
        stage('Deploy') {
            steps{
                sh 'docker-compose up'
            }
        }
    }
}