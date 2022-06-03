pipeline{
    agent any
    stages{
        stage("Deploy"){
            steps{
                println "Just dance"
                sh """
                echo $BUILD_NUMBER $BRANCH $SERVERIPS
                """
            }
        }
    }
}