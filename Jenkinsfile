pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'ramana0410/my-node-app'
        DOCKER_CREDENTIALS_ID = 'DOCKER_CREDENTIALS_ID'
        CONTAINER_NAME = "node-app-container" 
    }
    stages {
        stage("CheckOut Code") {
            steps {
                echo "📥 Cloning GitHub repo"
                git branch: 'main', url: 'https://github.com/Ramanakumar05/CI-Project.git'
            }
        }

        stage("Build Docker Image") {
            steps {
                echo "🐳 Building the Docker image"
                sh 'docker build -t $DOCKER_IMAGE:latest .'
            }
        }

        stage("Push Docker Image to DockerHub") {
            steps {
                echo "🔐 Logging into DockerHub and pushing image..."
                withCredentials([usernamePassword(
                    credentialsId: "${DOCKER_CREDENTIALS_ID}",
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker push $DOCKER_IMAGE:latest
                        docker logout
                    '''
                }
            }
        }

        stage("Deploy on EC2") {
            steps {
                echo "🚀 Deploying container on EC2..."
                sh '''
                    # Pull the latest image
                    docker pull $DOCKER_IMAGE:latest

                    # Stop and remove the old container if it's running
                    docker rm -f $CONTAINER_NAME || true

                    # Run the new container
                    docker run -d --name $CONTAINER_NAME -p 3000:3000 $DOCKER_IMAGE:latest
                '''
            }
        }
    }
}
