@NonCPS def builds() {
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
            node {
                unstash 'sources'

                stage("Build ${project}") {
                    docker.image('java:8').withRun() { c ->
                        sh "./gradlew ${project}:build"
                    }
                }
            }
        }
    }

    return builds
}





node {
    stage('checkout') {
        checkout scm
        stash name: 'sources'
    }
}

parallel builds

node {

    stage("Done") {
        sh "echo Done"
    }
}