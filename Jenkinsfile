pipeline {
    agent any

    tools {
        maven 'Maven 3.9.6' // Configure this in Jenkins Global Tool Configuration
        jdk 'JDK 11' // Configure this in Jenkins Global Tool Configuration
    }

    parameters {
        choice(name: 'TEST_SUITE', choices: ['calculatortestng.xml', 'src/test/resources/calculatortestng.xml'], description: 'Select TestNG suite to run')
        booleanParam(name: 'SKIP_TESTS', defaultValue: false, description: 'Skip test execution')
    }

    environment {
        MAVEN_OPTS = '-Xmx1024m'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from repository...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            when {
                expression { params.SKIP_TESTS == false }
            }
            steps {
                echo "Running tests from suite: ${params.TEST_SUITE}"
                bat "mvn test -Dsurefire.suiteXmlFiles=${params.TEST_SUITE}"
            }
            post {
                always {
                    // Publish TestNG Results
                    step([$class: 'Publisher',
                          reportFilenamePattern: '**/testng-results.xml'])

                    // Archive test reports
                    archiveArtifacts artifacts: 'test-output/**/*', allowEmptyArchive: true
                    archiveArtifacts artifacts: 'extentReport/**/*', allowEmptyArchive: true

                    // Publish JUnit test results
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Generate Reports') {
            steps {
                echo 'Generating test reports...'
                bat 'mvn surefire-report:report-only'
            }
        }

        stage('Package') {
            when {
                expression { params.SKIP_TESTS == false && currentBuild.result != 'FAILURE' }
            }
            steps {
                echo 'Packaging the application...'
                bat 'mvn package -DskipTests'
            }
        }

        stage('Archive Artifacts') {
            steps {
                echo 'Archiving build artifacts...'
                archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
                archiveArtifacts artifacts: '**/target/surefire-reports/**/*', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs(deleteDirs: true,
                    notFailBuild: true,
                    patterns: [[pattern: 'target/**', type: 'INCLUDE'],
                               [pattern: 'test-output/**', type: 'INCLUDE']])
        }
        success {
            echo 'Pipeline completed successfully!'
            emailext (
                subject: "SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """<p>SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
                        <p>Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>""",
                to: "${env.CHANGE_AUTHOR_EMAIL}",
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
        }
        failure {
            echo 'Pipeline failed!'
            emailext (
                subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
                        <p>Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>""",
                to: "${env.CHANGE_AUTHOR_EMAIL}",
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
        }
        unstable {
            echo 'Pipeline is unstable!'
        }
    }
}
