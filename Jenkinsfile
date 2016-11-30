def builds = [:]
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
            docker.image('java:8').withRun("") {
                sh "env"
                sh "echo \$PATH"
                sh "ls /usr/bin | grep java"
                sh "./gradlew ${project}:build"
            }
        }

    }
}

node {
    stage('Checkout') {
        checkout scm
        stash name: 'sources'
        sh "docker --version"
    }

    stage('Build') {
        parallel builds
    }

    stage("Done") {
        sh "echo Done"
    }
}