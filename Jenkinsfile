pipeline {
    agent any
    tools {
        maven '3.6.3'
        jdk '1.8.0_221'
    }
    stages {
            stage('Build') {
                steps {
                    bat 'mvn clean install'
                }
            }
            stage('ConnectBuild') {
                         docker.withRegistry('https://registry.example.com/', 'svc-acct') {    
                steps {
                    bat 'docker-compose --version'
                    bat 'docker --version'
                }
            }
        }
    }
}
