node {
    stage 'checkout'
    checkout scm

    docker.withServer("${env.DOCKER_HOST}", "${env.DOCKER_CREDENTIALS_ID}") {
        docker.image('python').inside {
            stage 'Pew Pew'
            sh "ls"
        }
    }

    stage 'post'
    sh "echo Hello"
}