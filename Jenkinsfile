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

for (int i = 0; i < projects.size(); i++) {
    def project = projects[i]

    builds["Build ${project}"] = {
        if (project == "account-service") {
            stage("Build ${project}") {

                gradleBuilder.inside() {

                    sh "gradle --project-cache-dir=${pwd()}/${project}/.gradle ${project}:build --info"
//                    sh "echo ${pwd()}/${project}/build/allure-results/*.xml"
//                    stash name: "allure-results", includes: "${pwd()}/${project}/build/allure-results/*.xml"
                }
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
        try {
            stage('Checkout') {
                checkout scm
                stash name: 'sources'
            }

            gradleBuilder = docker.build('gradle_builder', 'jenkins/gradle-builder')

            stage('Build') {
                parallel builds
            }

            stage("Push images") {
                sh "echo Push images"
            }

            stage('Component tests') {
//            parallel componentTests
            }

            stage('Integration tests') {
                sh "echo \"Integration tests\""
            }

            stage('Publish test reports') {
                allureBuilder = docker.build('allure_builder', 'jenkins/allure-builder')
                allureBuilder.inside() {
                    sh "allure generate ${pwd()}/account-service/build/allure-results -o ${pwd()}/account-service/build/allure-reports"
                }
                allure([commandline: 'allure', includeProperties: false, jdk: '', results: [[path: "${pwd()}/account-service/build/allure-results"]]])
            }

            stage('Release') {
//            def release = input(message: 'Do you want to release this build?',
//                    parameters: [[$class      : 'BooleanParameterDefinition',
//                                  defaultValue: false,
//                                  description : 'Ticking this box will do a release',
//                                  name        : 'Release']])
//
//            if (release) {
//                stage("Iterate version") {
//                    sh "echo \"Iterate version\""
//                }
//
//                stage("Create docker image tag") {
//                    sh "echo \"Create docker image tag\""
//                }
//
//                stage("Push docker image tag") {
//                    sh "echo \"Push docker image tag\""
//                }
//
//                stage("Deploy on production") {
//                    sh "echo \"Push docker image tag\""
//                }
//            }
            }

            stage('Done') {
                sh "echo Done"
            }
        } catch (Exception ex) {
            sh "echo ${ex.message}"
        }
    }
}