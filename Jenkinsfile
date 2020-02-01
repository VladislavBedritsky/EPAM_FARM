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
                sh 'docker-compose build'
                sh 'docker-compose up -d'
            }
        }
    }
}