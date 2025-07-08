pipeline{
    agent any
    environment {
        DOCKER_IMAGE = 'yourdockerhubusername/my-node-app:latest'
        DOCKER_CREDENTIALS_ID = 'dockerhub'
    }
    stages{
        stage("CheckOut Code")
        {
            steps
            {
                echo "Cloning Github repo"
                git branch: 'main' ,url:''
            }
        }

        stage("Build Docker Image")
        {
            steps{
                echo "Building the Docker image"
                sh 'docker build -t $DOCKER_IMAGE .'
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
    }
}