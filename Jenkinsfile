#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh '''
		            hostname
		            ls /
                    ls /tmp
                '''
                sshagent(['ssh-key-use-it']) {
                    // some block
                    //sh 'ssh -o StrictHostKeyChecking=no pi@$SIP"
                    //sh "whoami"

                    sh '''
                        ssh -o StrictHostKeyChecking=no -tt root@$SIP2 '
                            whoami;
                            hostname;
                            echo "$WORKSPACE";

                            if [ "$( docker container inspect -f "{{.State.Status}}" ${DOCKERNAME} )" == "running" ]; then
                                docker stop ${DOCKERNAME2}
                            fi;

                            docker start ${DOCKERNAME2};
                        '
                    '''
                }
            }
        }
    }
}
