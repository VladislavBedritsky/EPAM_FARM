pipeline {
    agent any
    stages {
        stage ('SNAPSHOT') {
            steps {

                withSonarQubeEnv('Sonarqube') {
                    sh 'mvn clean install -Dmaven.test.failure.ignore=true sonar:sonar'
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
            }
            post {
                always {
                    junit '**/surefire-reports/*.xml'
                    recordIssues(tools: [checkStyle(), pmdParser(), spotBugs(useRankAsPriority: true)])
                }
            }
        }

        stage('RELEASE') {
            steps {
                script {

                    def ret = sh(script: 'curl -u admin:password123  -s -o /dev/null -w "%{http_code}" http://192.168.99.1:8081/artifactory/api/storage/libs-release-local/org/example/org.example.EPAM_FARM/1.92/org.example.EPAM_FARM-1.92.pom', returnStdout: true)
                    if (ret == "200") {
                        currentBuild.result = 'FAILURE'
                        error "release failed"
                    }

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
                        mail bcc: '', body: 'Release stage failed.', cc: '', from: '', replyTo: '', subject: 'RELEASE FAILED', to: 'uladzislau_biadrytski@epam.com'
                    }
                }
            }
        }

        stage('DEPLOY') {
            steps {
                script {
                    sh 'mvn clean install -Dmaven.test.failure.ignore=true'
                    deploy adapters: [tomcat8(credentialsId: 'cd34afab-d0bd-4e08-949e-d2f2ebf703ef', path: '', url: 'http://tomcat:8080')], contextPath: null, war: 'rest/target/*.war'
                    deploy adapters: [tomcat8(credentialsId: 'cd34afab-d0bd-4e08-949e-d2f2ebf703ef', path: '', url: 'http://tomcat:8080')], contextPath: null, war: 'web-app/target/*.war'
                    sh 'sleep 10'

                    def statusRest = sh(script: 'curl -s -o /dev/null -w "%{http_code}" http://192.168.99.1:8087/rest-1.92/', returnStdout: true)
                    if (statusRest != "200") {
                        currentBuild.result = 'FAILURE'
                        error "deploy failed"
                    }

                    def statusWebApp = sh(script: 'curl -s -o /dev/null -w "%{http_code}" http://192.168.99.1:8087/web-app-1.92/', returnStdout: true)
                    if (statusWebApp != "200") {
                        currentBuild.result = 'FAILURE'
                        error "deploy failed"
                    }
                }
            }
            post {
                failure {
                    script {
                        mail bcc: '', body: 'Deploy stage failed.', cc: '', from: '', replyTo: '', subject: 'DEPLOY FAILED', to: 'uladzislau_biadrytski@epam.com'
                    }
                }
            }
        }
    }
}

