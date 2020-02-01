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
                sh 'sudo docker-compose build'
                sh 'docker-compose up -d'
            }
        }
    }
}