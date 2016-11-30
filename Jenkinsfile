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

node {
    stage('checkout') {
        checkout scm
    }

    parallel builds

    stage("Done") {
        sh "echo Done"
    }
}