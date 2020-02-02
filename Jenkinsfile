pipeline {
    agent any
    tools {
        maven '3.6.3'
        jdk '1.8.0_221'
    }
    stages {
            def myImg
            stage('Build') {
                steps {
                    bat 'mvn clean install'
                }
            }
            stage('ConnectBuild') {
                steps {
                     myImg = docker.build 'hello-world'
                }
            }
    }
}
