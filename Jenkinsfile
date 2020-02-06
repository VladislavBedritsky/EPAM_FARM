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
                timeout(time: 1, unit: 'HOURS') {
                 def qg = waitForQualityGate()
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
