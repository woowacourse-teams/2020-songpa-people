node {
    stage ('clone') {
        checkout scm
    }
    stage('build') {
        sh './gradlew clean build'
    }
    stage('sonarqube') {
        sh './gradlew :hashtagmap-admin:sonarqube'
        sh './gradlew --stop'

        sh './gradlew :hashtagmap-batch:sonarqube'
        sh './gradlew --stop'

        sh './gradlew :hashtagmap-core:sonarqube'
        sh './gradlew --stop'

        sh './gradlew :hashtagmap-event:sonarqube'
        sh './gradlew --stop'

        sh './gradlew :hashtagmap-instagram-crawler:sonarqube'
        sh './gradlew --stop'

        sh './gradlew :hashtagmap-instagram-service:sonarqube'
        sh './gradlew --stop'

        sh './gradlew :hashtagmap-kakao-api:sonarqube'
        sh './gradlew --stop'

        sh './gradlew :hashtagmap-kakao-scheduler:sonarqube'
        sh './gradlew --stop'

        sh './gradlew :hashtagmap-web:sonarqube'
        sh './gradlew --stop'
    }
}