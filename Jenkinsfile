pipeline {
    agent any
    stages {           

            stage('ConnectBuild') {
                steps {
                     sh 'sudo service docker start && docker run hello-world'
                }
            }
    }
}
