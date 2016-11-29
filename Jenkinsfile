node {
    stage 'checkout'
    checkout scm

    docker.image('jenkins_slave').inside {
        node {
            stage 'main'
            sh "echo 'Hello World From Docker'"
        }
    }
    stage 'post'
    sh "echo 'Hello World'"
}