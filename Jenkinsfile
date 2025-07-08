pipeline{
    agent any
    environment {
        DOCKER_IMAGE = 'ramana0410/my-node-app:latest'
        DOCKER_CREDENTIALS_ID = 'DOCKER_CREDENTIALS_ID'
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