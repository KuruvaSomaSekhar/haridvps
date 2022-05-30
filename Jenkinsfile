pipeline {
    agent any
    environment {
     BRANCH_NAME = "${GIT_BRANCH.split("/")[1]}"
  }
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
                    echo $BRANCH_NAME
                    aws s3 ls s3://artifacthari
                    aws s3 cp target/hello-*.war s3://artifacthari/$BRANCH_NAME/$BUILD_NUMBER/
                """
            }

        }
        stage("Deploy"){
                steps {
                println "Here we deploy artifacts"
                sh "scp -o StrictHostKeyChecking=no -i /tmp/devops-hari.pem target/hello-*.war ec2-user@172.31.6.12:/var/lib/tomcat/webapps/"
            }
        }
    }
}