stage 'checkout'

docker.image('java:8-jre-alpine').inside {
    node {
        checkout scm
        stage 'main'
        sh 'echo "Hello World From Docker"'
    }
}
stage 'post'
sh 'echo "Hello World"'