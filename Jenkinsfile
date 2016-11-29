stage 'checkout'
checkout scm

docker.image('java:8-jre-alpine').inside {
    node {
        stage 'main'
        sh 'echo "Hello World From Docker"'
    }
}
stage 'post'
sh 'echo "Hello World"'