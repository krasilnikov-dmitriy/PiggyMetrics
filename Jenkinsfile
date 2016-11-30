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
            def containerId = "${env.NODE_NAME}".split("-").last()
            print "Hello from ${containerId}"
            docker.image('java:8').withRun("-t --entrypoint cat") { c ->
                withEnv(["JAVA_HOME_PEW_PEW=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.111-1.b15.el7_2.x86_64"]) {
                    sh "pwd"
                    sh "ls -ltr"
                    sh "java -version"
                    sh "readlink -f /usr/bin/java"
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
        sh "env"
    }

    stage('Build') {
        parallel builds
    }

    stage("Done") {
        sh "echo Done"
    }
}