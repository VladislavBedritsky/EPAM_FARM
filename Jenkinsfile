pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'mvn clean install'
            }
        }
        stage('ConnectBuild') {
            steps {
                echo 'docker-compose up'
            }
        }
    }
}
