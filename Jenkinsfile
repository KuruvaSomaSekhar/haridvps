pipeline {
    agent any
    stages{
        stage("Clone"){
            steps {
                println "Here we clone code"
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], userRemoteConfigs: [[ url: 'https://github.com/KuruvaSomaSekhar/boxfuse-sample-java-war-hello.git']]])
                sh "ls -lart ./*"
            }

        }
        stage("Build"){
             steps {
                println "Here we build code"
                sh "mvn clean package"
                sh "ls -l target/"
            }

        }
        stage("Upload artifacts"){

              steps {
                println "Here we upload artifacts"
                sh """
                    aws s3 ls s3://artifacthari
                    echo $BUILD_NUMBER
                    branch=$\(echo $GIT_BRANCH   | sed -e "s|origin/||g")
                    echo $branch
                    aws s3 cp target/hello-*.war s3://artifacthari/$branch/$BUILD_NUMBER/
                """
            }

        }
        stage("Deploy"){
                steps {
                println "Here we deploy artifacts"
            }
        }
    }
}