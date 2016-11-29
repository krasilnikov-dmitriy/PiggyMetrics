node {
    stage 'checkout'
    checkout scm

    docker.image('python').withRun() { c ->
        stage 'Pew Pew'
        sh "ls"
    }

    stage 'post'
    sh "echo Hello"
}