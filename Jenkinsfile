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

                        ssh -o StrictHostKeyChecking=no -tt pi@$SIP '
                            whoami;
                            hostname;
                            echo "$WORKSPACE";

                            if [ "$( docker container inspect -f "{{.State.Status}}" '$DOCKERNAME' )" == "running" ]; then
                                docker stop ${DOCKERNAME}
                            fi;

                            docker run -d \
                                --name '$DOCKERNAME' \
                                -p 53:53/tcp -p 53:53/udp \
                                -p 80:80 \
                                -e TZ="America/Chicago" \
                                -v "/tmp/etc.pihole/:/etc/pihole/" \
                                -v "/tmp/etc.dnsmasq.d/:/etc/dnsmasq.d/" \
                                --dns=127.0.0.1 --dns=1.1.1.1 \
                                --restart=unless-stopped \
                                --hostname '$HNAME' \
                                -e VIRTUAL_HOST="'$HNAME'" \
                                -e PROXY_LOCATION="'$HNAME'" \
                                -e ServerIP="'$SIP'" \
                                pihole/pihole:latest;

                            printf "Starting up pihole container ";

                            for i in $(seq 1 20); do
                                if [ "$(docker inspect -f "{{.State.Health.Status}}" '$DOCKERNAME')" == "healthy" ] ; then
                                    printf " OK"
                                    echo -e "\n$(docker logs '$DOCKERNAME' 2> /dev/null | grep "password:") for your $HNAME: https://'$SIP'/admin/"
                                    exit 0
                                else
                                    sleep 3
                                    printf "."
                                fi

                                if [ $i -eq 20 ] ; then
                                    echo -e "\nTimed out waiting for Pi-hole start, consult your container logs for more info (`docker logs '$DOCKERNAME'`)"
                                    exit 1
                                fi
                            done;
                        '
                    '''
                }
            }
        }
    }
}
