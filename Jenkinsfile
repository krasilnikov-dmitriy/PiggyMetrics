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

node {
    stage('checkout') {
        checkout scm
    }

    def builds = [:]
    projects.each { project ->
        builds["Build ${project}"] = {
            docker.image('java:8').withRun() { c ->
                stage("Build ${project}") {
                    sh "./gradlew ${project}:build"
                }
            }
        }
    }
    parallel builds

    stage("Done") {
        sh "echo Done"
    }
}