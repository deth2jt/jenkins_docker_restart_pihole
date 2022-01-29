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
                    ssh -o "StrictHostKeyChecking no" -C pi@172.17.0.1 'hostname'
                '''
            }
        }
    }
}
