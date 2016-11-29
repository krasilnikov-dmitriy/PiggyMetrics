node {
    stage 'checkout'
    checkout scm

    docker.withServer("${env.DOCKER_HOST}", "${env.DOCKER_CREDENTIALS_ID}") {
        docker.image('node').withRun('-p 8080:80') {c ->
            stage 'Pew Pew'
            sh "ls"
        }
    }

    stage 'post'
    sh "echo Hello"
}