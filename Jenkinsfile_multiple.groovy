pipeline{
    agent any
    stages{
        stage("Deploy"){
            steps{
                println "Just dance"
            }
        }
    }
}