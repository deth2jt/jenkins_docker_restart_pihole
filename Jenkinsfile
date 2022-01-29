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
                    ip add
                    ifconfig
                    ssh -C pi@$SNAME 'hostname'
                '''
            }
        }
    }
}
