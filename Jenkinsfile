pipeline {
    agent any
    stages {
        stage('Maven') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Docker') {
            steps {
                sh 'docker-compose -H localhost:2375 up -d'
            }
        }
    }
}
