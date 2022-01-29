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
                    sh "ssh -o StrictHostKeyChecking=no pi@$SIP"
                    sh "whoami"
                }
            }
        }
    }
}
