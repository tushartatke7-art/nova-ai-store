pipeline {
    agent any
    environment {
        APP_NAME = 'nova-ai-store'
        VERSION  = '1.0.0'
    }
    stages {
        stage('Checkout') {
            steps {
                echo 'Pulling code from GitHub...'
                checkout scm
            }
        }
        stage('Build') {
            steps {
                echo 'Building with Maven...'
                sh 'mvn package -DskipTests -q'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
        }
        stage('Docker Build') {
            steps {
                echo 'Building Docker image...'
                sh 'docker build -t ${APP_NAME}:${VERSION} .'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying NOVA AI Store...'
                sh '''
                    docker stop nova-app || true
                    docker rm nova-app || true
                    docker run -d \
                        --name nova-app \
                        -p 8080:8080 \
                        -e ANTHROPIC_API_KEY=${ANTHROPIC_API_KEY} \
                        nova-ai-store:1.0.0
                '''
            }
        }
        stage('Health Check') {
            steps {
                echo 'Checking app is healthy...'
                sh 'sleep 15'
                sh 'curl -f http://localhost:8080/api/health'
            }
        }
    }
    post {
        success {
            echo '✅ Pipeline SUCCESS — NOVA AI Store deployed!'
        }
        failure {
            echo '❌ Pipeline FAILED — check logs!'
        }
    }
}
