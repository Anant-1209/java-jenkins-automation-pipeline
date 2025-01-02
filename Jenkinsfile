// pipeline {
//     agent any

//     environment {
//         SONAR_SCANNER_PATH = 'C:\\Program Files\\sonar-scanner-6.2.1.4610-windows-x64\\bin'
//     }

//     stages {
//         stage('Checkout') {
//             steps {
//                 checkout scm
//             }
//         }

//         stage('Build and Test') {
//             steps {
//                 script {
//                     def mvnHome = tool 'Maven3' // Use the Maven tool configured in Jenkins
//                     withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
//                         bat 'mvn clean test' // Run tests
//                     }
//                 }
//             }
//         }

//         stage('SonarQube Analysis') {
//             environment {
//                 SONAR_TOKEN = credentials('java-jenkins-automation-pipeline') // Ensure the correct credential ID
//             }
//             steps {
//                 script {
//                     def mvnHome = tool 'Maven3'
//                     withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
//                         bat """
//                            mvn sonar:sonar \
//                            -Dsonar.projectKey=java-jenkins-automation-pipeline \
//                            -Dsonar.projectName='java-jenkins-automation-pipeline' \
//                            -Dsonar.host.url=http://localhost:9000 \
//                            -Dsonar.login=%SONAR_TOKEN% \
//                            -Dsonar.jacoco.reportPaths=target/jacoco.xml
//                         """
//                     }
//                 }
//             }
//         }

//         stage('Generate JaCoCo Coverage Report') {
//             steps {
//                 script {
//                     def mvnHome = tool 'Maven3'
//                     withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
//                         bat 'mvn verify jacoco:report' // Generate code coverage report
//                     }
//                 }
//             }
//         }

//         stage('Publish Coverage Report') {
//             steps {
//                 jacoco(
//                     execPattern: '**/target/jacoco.exec',
//                     classPattern: '**/target/classes',
//                     sourcePattern: '**/src/main/java',
//                     inclusionPattern: '**/*.java',
//                     exclusionPattern: '**/test/**'
//                 )
//             }
//         }
//     }

//     post {
//         success {
//             echo 'Pipeline executed successfully!'
//         }
//         failure {
//             echo 'Pipeline failed!'
//         }
//     }
// }


pipeline {
    agent any

    environment {
        SONAR_SCANNER_PATH = 'C:\\Program Files\\sonar-scanner-6.2.1.4610-windows-x64\\bin'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Anant-1209/java-jenkins-automation-pipeline.git'
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    def mvnHome = tool 'Maven3' // Use the Maven tool configured in Jenkins
                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                        bat 'mvn clean test' // Run tests
                    }
                }
            }
        }

        stage('Run Tests and Code Coverage') {
            parallel {
                stage('Run Tests') {
                    steps {
                        script {
                            def mvnHome = tool 'Maven3' // Use the Maven tool configured in Jenkins
                            withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                                bat 'mvn test' // Run unit tests
                            }
                        }
                    }
                }
                stage('Code Coverage') {
                    steps {
                        script {
                            def mvnHome = tool 'Maven3' // Use the Maven tool configured in Jenkins
                            withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                                bat 'mvn jacoco:report' // Generate code coverage report using JaCoCo
                            }
                        }
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            environment {
                SONAR_TOKEN = credentials('java-jenkins-automation-pipeline') // Ensure the correct credential ID
            }
            steps {
                script {
                    def mvnHome = tool 'Maven3' // Use the Maven tool configured in Jenkins
                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                        bat '''
                            mvn sonar:sonar \
                           -Dsonar.projectKey=java-jenkins-automation-pipeline \
                           -Dsonar.projectName='java-jenkins-automation-pipeline' \
                           -Dsonar.host.url=http://localhost:9000 \
                           -Dsonar.login=%SONAR_TOKEN% \
                        '''
                    }
                }
            }
        }

        stage('Success') {
            steps {
                echo 'Build, Test, SonarQube Analysis, and Code Coverage completed successfully!'
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed! Please check the logs.'
        }
    }
}