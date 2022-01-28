# jenkins_docker_restart_pihole
## Build <br />
docker build -t jenkinsdev .  <br />
# Run  <br />
docker run -p 8080:8080 --add-host meine.host.com:host-gateway -v home/pi/pihole/etc-dnsmasq.d:/tmp/etc-dnsmasq.d -v  home/pi/pihole/etc-pihole:/tmp/etc-pihole jenkinsdev  <br />

