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
//                         bat 'mvn clean verify'
//                     }
//                 }
//             }
//         }

//         stage('Sonar Analysis') {
//             environment {
//                 SONAR_TOKEN = credentials('java-jenkins-automation-pipeline') // Ensure the correct credential ID
//             }
//             steps {
//                 script {
//                     def mvnHome = tool 'Maven3' // Use the Maven tool configured in Jenkins
//                     withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
//                         bat """
//                        mvn clean verify sonar:sonar \
//   -Dsonar.projectKey=java-jenkins-automation-pipeline \
//   -Dsonar.projectName='java-jenkins-automation-pipeline' \
//   -Dsonar.host.url=http://localhost:9000 \
//   -Dsonar.login=%SONAR_TOKEN%
//                         """
//                     }
//                 }
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
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    def mvnHome = tool 'Maven3' // Use the Maven tool configured in Jenkins
                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                        bat 'mvn clean verify'
                    }
                }
            }
        }

        stage('Sonar Analysis') {
            steps {
                script {
                    def mvnHome = tool 'Maven3' // Use the Maven tool configured in Jenkins
                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                        bat """
                        mvn clean verify sonar:sonar ^
                          -Dsonar.projectKey=java-jenkins-automation-pipeline ^
                          -Dsonar.projectName='java-jenkins-automation-pipeline' ^
                          -Dsonar.host.url=http://localhost:9000 ^
                          -Dsonar.token=sqp_a3a0a49dcc451d12b2eb19e7a43b341c37e69faf
                        """
                    }
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
