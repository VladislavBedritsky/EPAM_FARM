pipeline {
    agent any
    stages {
        stage ('SNAPSHOT') {
            steps {

                withSonarQubeEnv('Sonarqube') {
                    sh 'mvn install -Dmaven.test.failure.ignore=true sonar:sonar'
                }

                timeout(time: 1, unit: 'HOURS') {
                    script {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            mail bcc: '', body: 'Tests in SonarQube are less than 80%', cc: '', from: '', replyTo: '', subject: 'SNAPSHOT FAILED', to: 'uladzislau_biadrytski@epam.com'
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }

                script {
                    def artServer = Artifactory.server('ARTIFACTORY_SERVER')
                    def buildInfo = Artifactory.newBuildInfo()
                    artServer.publishBuildInfo buildInfo
                }
            }
        }

        stage('RELEASE') {
            steps {
                script {
                    def artServer = Artifactory.server('ARTIFACTORY_SERVER')
                    def rtMaven = Artifactory.newMavenBuild()
                    rtMaven.tool = 'Maven-3.6'
                    def buildInfo = rtMaven.run pom: 'pom.xml',  goals: 'clean install'

                    rtMaven.resolver server: artServer, releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot'
                    rtMaven.deployer server: artServer, releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local'
                    artServer.publishBuildInfo buildInfo
                    rtMaven.deployer.deployArtifacts buildInfo
                }
            }
            post {
                failure {
                    script {
                        mail bcc: '', body: 'Artifactory stage failed.', cc: '', from: '', replyTo: '', subject: 'RELEASE FAILED', to: 'uladzislau_biadrytski@epam.com'
                    }
                }
            }
        }

    }
}
