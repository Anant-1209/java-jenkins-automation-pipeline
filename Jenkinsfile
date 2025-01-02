pipeline {
    agent any

    environment {
        SONAR_SCANNER_PATH = 'C:\\Program Files\\sonar-scanner-6.2.1.4610-windows-x64\\bin'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    def mvnHome = tool 'Maven3' // Use the Maven tool configured in Jenkins
                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                        bat 'mvn clean install' // Use clean install instead of clean verify to ensure build and tests run
                    }
                }
            }
        }

        stage('Sonar Analysis') {
            environment {
                SONAR_TOKEN = credentials('java-jenkins-automation-pipeline') // Ensure the correct credential ID
            }
            steps {
                script {
                    def mvnHome = tool 'Maven3' // Use the Maven tool configured in Jenkins
                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                        bat """
                           mvn sonar:sonar \
                           -Dsonar.projectKey=java-jenkins-automation-pipeline \
                           -Dsonar.projectName='java-jenkins-automation-pipeline' \
                           -Dsonar.host.url=http://localhost:9000 \
                           -Dsonar.login=%SONAR_TOKEN% \
                           -Dsonar.jacoco.reportPaths=target/jacoco.xml
                        """
                    }
                }
            }
        }

        stage('Generate JaCoCo Coverage Report') {
            steps {
                script {
                    // Generate JaCoCo code coverage report after tests have run
                    def mvnHome = tool 'Maven3'
                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                        bat 'mvn jacoco:report' // Generates the coverage report based on jacoco.exec
                    }
                }
            }
        }

        stage('Publish JaCoCo Coverage') {
            steps {
                script {
                    // Publish the JaCoCo code coverage report in Jenkins
                    jacoco(
                        execPattern: '**/target/jacoco.exec', // JaCoCo execution file pattern
                        classPattern: '**/target/classes', // Classes directory pattern
                        sourcePattern: '**/src/main/java', // Source code directory pattern
                        inclusionPattern: '**/*.java', // Include Java files
                        exclusionPattern: '**/test/**' // Exclude test files
                    )
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
