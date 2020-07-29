node {
    stage ('clone') {
        checkout scm
    }
    stage('build') {
        sh './gradlew clean build'
    }
}