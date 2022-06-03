pipeline{
    agent any
    stages{
        stage("Deploy"){
            steps{
                script {
                println "Just dance"
                sh '''
                echo $BUILD_NUMBER $BRANCH $SERVERIPS
                IFS="," read -r -a MYSERVERIPS <<< $SERVERIPS
                echo ${MYSERVERIPS[@]}

                for server in ${MYSERVERIPS[@]}
                do
                echo $server
                echo "SCP command here"
                aws s3 cp s3://jenkins101/origin/$BRANCH_NAME/${BUILD_NUMBER}/hello-${BUILD_NUMBER}.war .
                scp -i /tmp/kiran.pem hello-${BUILD_NUMBER}.war ec2-user@$server:/var/lib/tomcat/webapps
                done

                '''
                }
            }
        }
    }
}