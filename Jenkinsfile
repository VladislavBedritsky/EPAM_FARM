pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                dir('/') {
                    sh 'mvn install'
                }
            }
        }
        stage('Deploy') {
            steps{
                dir('/') {
                    sh 'sudo docker-compose up'
                }
            }
        }
    }
}