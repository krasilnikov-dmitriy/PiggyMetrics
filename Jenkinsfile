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
            sh "ls -ltr /home/jenkins"
            sh "ls -ltr /home/jenkins/.docker/"
            docker.image('java:8').inside("--volumes-from jenkins_workspace_1") {
                sh "ls -ltr"
//                sh "java -version"
//                sh "readlink -f /usr/bin/java"
//                sh "./gradlew ${project}:build"
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