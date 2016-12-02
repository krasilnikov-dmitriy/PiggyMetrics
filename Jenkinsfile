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

for (int i = 0 ; i < projects.size(); i++) {
    def project = projects[i]

    builds["Build ${project}"] = {

        stage("Build ${project}") {
            def builder = docker.build('gradle-builder', 'jenkins/builders/GradleDockerfile')
            docker.image('java:8').inside() {
                sh "gradle ${project}:build"
            }
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