pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('ConnectBuild') {
            steps {
                bat 'docker-compose up'
            }
        }
    }
}
