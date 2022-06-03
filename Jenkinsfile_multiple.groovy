pipeline{
    agent any
    stages{
        stage("Deploy"){
            steps{
                script {
                println "Just dance"
                sh """
                echo $BUILD_NUMBER $BRANCH $SERVERIPS
                IFS="," read -r -a MYSERVERIPS <<< $SERVERIPS
                echo $MYSERVERIPS[@]

                """
                }
            }
        }
    }
}