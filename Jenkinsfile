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
                    ssh -C pi@$SIP 'hostname'
                '''
            }
        }
    }
}
