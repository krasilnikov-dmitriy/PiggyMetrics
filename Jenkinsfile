def builds = [:]
def componentTests = [:]
def projects = [
        'account-service',
        'auth-service',
        'config',
        'gateway',
        'monitoring',
        'notification-service',
        'registry',
        'statistics-service'
]

def gradleBuilder

for (int i = 0 ; i < projects.size(); i++) {
    def project = projects[i]

    builds["Build ${project}"] = {

        stage("Build ${project}") {
//            gradleBuilder.inside() {
//                sh "ls -ltr"
//                sh "echo \$USER"
//                sh "gradle --version > pewpewpewpew.txt"
//                def lines = sh(script: 'gradle --version', returnStdout: true).split("\r?\n")
//                println(lines)
//                sh "echo ${lines}"
//            }
        }
    }

    componentTests["Component tests for ${project}"] = {
        docker.image('java:8').inside() {
            sh "echo \"Component tests for ${project}\""
        }
    }
}

node {
    ws("${pwd()}/${java.util.UUID.randomUUID()}") {
        stage('Checkout') {
            checkout scm
            stash name: 'sources'
            sh "while date ; do /bin/sleep 0.001; done"
        }

        gradleBuilder = docker.build('gradle_builder', 'jenkins/gradle-builder')

        docker.image('gradle_builder').inside() {
            sh "ls -ltr"
            sh "uname -a"
            sh "while date ; do /bin/sleep 0.001; done"
            sh "wget -v https://services.gradle.org/distributions/gradle-3.2-all.zip"
            sh 'gradle --version --debug --stacktrace > version_debug.out'
            sh 'ls -ltr'
        }

        stage('Build') {
            parallel builds
        }

        stage("Push images") {
            sh "echo Push images"
        }

        stage('Component tests') {
            parallel componentTests
        }

        stage('Integration tests') {
            sh "echo \"Integration tests\""
        }

        stage('Release') {
            def release = input(message: 'Do you want to release this build?',
                    parameters: [[$class      : 'BooleanParameterDefinition',
                                  defaultValue: false,
                                  description : 'Ticking this box will do a release',
                                  name        : 'Release']])

            if (release) {
                stage("Iterate version") {
                    sh "echo \"Iterate version\""
                }

                stage("Create docker image tag") {
                    sh "echo \"Create docker image tag\""
                }

                stage("Push docker image tag") {
                    sh "echo \"Push docker image tag\""
                }

                stage("Deploy on production") {
                    sh "echo \"Push docker image tag\""
                }
            }
        }

        stage('Done') {
            sh "echo Done"
        }
    }
}