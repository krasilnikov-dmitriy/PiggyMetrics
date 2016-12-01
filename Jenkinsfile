def builds = [:]
def projects = [
        'account-service'
]

for (int i = 0 ; i < projects.size(); i++) {
    def project = projects[i]

    builds["Build ${project}"] = {

        stage("Build ${project}") {
            def containerId = "${env.NODE_NAME}".split("-").last()
            docker.image('java:8').inside() {
                sh "java -version"
                sh "ls -ltr"
                sh "pwd"
                sh "./gradlew ${project}:build --stacktrace"
            }
        }

    }
}

node {
    stage('Checkout') {
        checkout scm
        stash name: 'sources'
        sh "docker --version"
        sh "env"
    }

    stage('Build') {
        parallel builds
    }

    stage("Done") {
        sh "echo Done"
    }
}