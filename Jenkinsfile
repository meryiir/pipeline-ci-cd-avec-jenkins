pipeline {
    agent any
    
    tools {
        maven 'maven'
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Récupération du code depuis GitHub...'
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo 'Build Maven en cours...'
                sh 'mvn clean package'
            }
        }
        
        stage('Test') {
            steps {
                echo 'Exécution des tests...'
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Build Docker Image') {
            steps {
                echo 'Création de l image Docker...'
                script {
                    dockerImage = docker.build("demo-app:${env.BUILD_NUMBER}")
                }
            }
        }
        
        stage('Push Docker Image') {
            steps {
                echo 'Tag de l image Docker...'
                script {
                    dockerImage.tag("latest")
                }
            }
        }
        
        stage('Stop Previous Container') {
            steps {
                script {
                    sh '''
                        docker stop demo-container || true
                        docker rm demo-container || true
                    '''
                }
            }
        }
        
        stage('Run Container') {
            steps {
                echo 'Lancement du conteneur Docker...'
                script {
                    sh "docker run -d --name demo-container -p 8080:8080 demo-app:${env.BUILD_NUMBER}"
                }
            }
        }
    }
    
    post {
        success {
            echo 'Pipeline réussi ! Application déployée sur http://localhost:8080'
        }
        failure {
            echo 'Pipeline échoué !'
        }
        always {
            echo 'Pipeline terminé.'
        }
    }
}
