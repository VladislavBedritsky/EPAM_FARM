pipeline {
    agent any
    stages {
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('Sonarqube') {
                    sh 'mvn install -Dmaven.test.failure.ignore=true sonar:sonar'
                }
            }
        }
        stage("Quality Gate"){
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    script {
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
    }
}

