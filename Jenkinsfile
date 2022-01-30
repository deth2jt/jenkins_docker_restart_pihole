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
                        set -aex
                        ssh -o StrictHostKeyChecking=no -tt root@$SIP2 >> ENDSSH
                            whoami
                            hostname
                            echo "$WORKSPACE"

                            if [ "$( docker container inspect -f "{{.State.Status}}" ${DOCKERNAME} )" == "running" ]; then
                                docker stop ${DOCKERNAME2}
                            fi

                             docker stop ${DOCKERNAME2}
                        ENDSSH
                    '''
                }
            }
        }
    }
}
