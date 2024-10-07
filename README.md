# jenkins_docker_restart_pihole
## Build <br />
docker build -t jenkinsdev .  <br />
# Run  <br />
docker run -p 8080:8080  -v "/home/pi/pihole/etc-dnsmasq.d":"/tmp/etc.dnsmasq.d" -v "/home/pi/pihole/etc-pihole":"/tmp/etc.pihole" -v "/home/pi/.key":"/tmp/ssh-key.priv" jenkinsdev  <br />
Assist clients with automation needs by providing tailored, flexible solutions that meet their unique requirements. Manage all aspects of the process, from conception and design to delivery and maintenance, ensuring a seamless transformation of their workflows into more agile and efficient environments
