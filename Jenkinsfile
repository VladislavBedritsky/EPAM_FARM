pipeline {
    agent any
    stages {
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('Sonarqube') {
                    sh 'mvn clean package sonar:sonar'
                }
            }
        }
        stage("Quality Gate"){
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    step {
                        def qg = waitForQualityGate()
                    }
                    step {
                        if (qg.status != 'OK') {
                            slackSend baseUrl: 'https://hooks.slack.com/services/'
                            channel: 'jenkins-pipeline-demo'
                            color: 'danger'
                            message: 'SonarQube Analysis Failed'
                            teamDomain: 'javahomecloud'
                            tokenCredentialId: 'slack-demo'
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                 }
            }
        }
    }
}
