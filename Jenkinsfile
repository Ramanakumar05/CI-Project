pipeline{
    agent any
    environment {
        DOCKER_IMAGE = 'ramana0410/my-node-app'
        DOCKER_CREDENTIALS_ID = 'DOCKER_CREDENTIALS_ID'
        CONTAIENR_NAME = "node-app-container"
    }
    stages{
        stage("CheckOut Code")
        {
            steps
            {
                echo "Cloning Github repo"
                git branch: 'main' ,url:'https://github.com/Ramanakumar05/CI-Project.git'
            }
        }

        stage("Build Docker Image")
        {
            steps{
                echo "Building the Docker image"
                sh 'docker build -t $DOCKER_IMAGE:latest .'
            }
        }

        stage('Push Docker Image to DockerHub') {
            steps {
                echo "🔐 Logging into DockerHub and pushing..."
                withCredentials([usernamePassword(
                    credentialsId: "${DOCKER_CREDENTIALS_ID}",
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) 
                {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker push $DOCKER_IMAGE
                        docker logout
                    '''
                }
            }
        }
        stage("Deploy")
        {
            steps{
                script{
                    
                    sh'''
                        # Pull the latest Image from the Docker hub
                        docker pull docker push ramana0410/my-node-app:latest

                        # Stop and remove the old container if running
                        docker rm -f $CONTAIENR_NAME || true

                        # Start a new container
                        docker run -d --name $CONTAIENR_NAME -p3000:3000 $DOCKER_IMAGE:latest
                    '''
                }
            }
        }
    }
}