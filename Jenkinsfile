pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

    }

    post {
        always {
            echo 'Pipeline finished.'
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'target/cucumber-reports.html', allowEmptyArchive: true
                    publishHTML(target: [
                        reportDir: 'target',
                        reportFiles: 'cucumber-reports.html',
                        reportName: 'Cucumber HTML Report'
                    ])

        }
        success {
            echo 'Build and tests succeeded!'
        }
        failure {
            echo 'Build or tests failed.'
        }
    }
}