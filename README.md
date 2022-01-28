# jenkins_docker_restart_pihole
## Build <br />
docker build -t jenkinsdev .  <br />
# Run  <br />
docker run -p 8080:8080 --add-host meine.host.com:host-gateway jenkinsdev  <br />

