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
            steps {
                bat 'docker run hello-world'
            }
        }
    }
}
