pipeline {
    agent any

    tools {
        // Specifying the tools to use for the pipeline (Maven & JDK)
        maven 'Maven 3.8.1'  // Use your version of Maven configured on Jenkins
        jdk 'JDK 11'         // Specify the Java version
    }

    environment {
        // Path to the ChromeDriver (adjust accordingly)
        CHROME_DRIVER_PATH = "C:\\Program Files\\chromedriver-win64\\chromedriver-win64"
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from your repository
                git 'https://github.com/Anant-1209/java-jenkins-automation-pipeline.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    // Run the Maven build
                    sh 'mvn clean install'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Run the Maven test
                    sh 'mvn test'
                }
            }
        }

        stage('Archive Results') {
            steps {
                // Archive test results (You can specify different location or log files)
                archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
                junit '**/target/test-*.xml'  // Include JUnit test reports
            }
        }
    }

    post {
        always {
            // Clean up any running processes or artifacts if needed
            cleanWs()
        }
        success {
            // Actions to take if the pipeline succeeds
            echo 'Pipeline executed successfully.'
        }
        failure {
            // Actions to take if the pipeline fails
            echo 'Pipeline failed.'
        }
    }
}
