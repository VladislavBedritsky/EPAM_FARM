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
                sh 'export DOCKER_HOST=127.0.0.1:2375'
                sh 'sudo docker-compose up'
            }
        }
    }
}