node {
    stage 'checkout'
    checkout scm

    docker.image('node').inside {
            stage 'main'
            sh "ls"
    }
    stage 'post'
    sh "echo Hello"
}