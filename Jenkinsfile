pipeline {
    agent any
    stages {           

            stage('ConnectBuild') {
                steps {
                     sh 'docker -H localhost:2375 ps -a'
                }
            }
    }
}
