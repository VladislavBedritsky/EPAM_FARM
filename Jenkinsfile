pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('ConnectBuild') {
            steps {
                sh 'docker -H localhost:2375 images'
                sh 'docker-compose up'
            }
        }
    }
}