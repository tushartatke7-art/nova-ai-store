pipeline {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-17'
            args '-v /root/.m2:/root/.m2 -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }

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
                echo 'Deploying app...'
                sh '''
                    docker stop nova-app || true
                    docker rm nova-app || true
                    docker run -d \
                        --name nova-app \
                        -p 8080:8080 \
                        nova-ai-store:1.0.0
                '''
            }
        }

        stage('Health Check') {
            steps {
                echo 'Checking app is healthy...'
                sh 'sleep 15'
                sh 'curl -f http://172.18.135.144:8080/api/health'
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline SUCCESS — BrewMind AI deployed!'
        }
        failure {
            echo '❌ Pipeline FAILED — check logs!'
        }
    }
}
