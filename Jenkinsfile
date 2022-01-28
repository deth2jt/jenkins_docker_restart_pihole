#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh '''
		    hostname
		    ls /
                    cp -r /tmp/etc-* $WORKSPACE/
                    echo "$WORKSPACE"

                    if [ "$( docker container inspect -f '{{.State.Status}}' $container_name )" == "running" ]; then
                        docker stop pihole

                    docker run -d \
                        --name pihole \
                        -p 53:53/tcp -p 53:53/udp \
                        -p 80:80 \
                        -e TZ="America/Chicago" \
                        -v "${WORKSPACE}/etc-pihole/:/etc/pihole/" \
                        -v "${WORKSPACE}/etc-dnsmasq.d/:/etc/dnsmasq.d/" \
                        --dns=127.0.0.1 --dns=1.1.1.1 \
                        --restart=unless-stopped \
                        --hostname pi.hole \
                        -e VIRTUAL_HOST="$HNAME" \
                        -e PROXY_LOCATION="$HNAME" \
                        -e ServerIP="$SIP" \
                        pihole/pihole:latest

                    printf 'Starting up pihole container '
                    for i in $(seq 1 20); do
                        if [ "$(docker inspect -f "{{.State.Health.Status}}" pihole)" == "healthy" ] ; then
                            printf ' OK'
                            echo -e "\n$(docker logs pihole 2> /dev/null | grep 'password:') for your pi-hole: https://${IP}/admin/"
                            exit 0
                        else
                            sleep 3
                            printf '.'
                        fi

                        if [ $i -eq 20 ] ; then
                            echo -e "\nTimed out waiting for Pi-hole start, consult your container logs for more info (`docker logs pihole`)"
                            exit 1
                        fi
                    done;
                '''
            }
        }
    }
}
