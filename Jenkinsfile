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
            docker.image('java:8').withRun("-t --entrypoint cat") {
                withEnv(["JAVA_HOME_PEW_PEW=/usr/lib/jvm/java-8-openjdk-amd64"]) {
                    sh "pwd"
                    sh "ls -ltr /usr/bin"
                    sh "java -version"
                    sh "./gradlew ${project}:build"
                }
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