#!/bin/bash
# Extended from https://github.com/jenkinsci/docker/blob/master/README.md
#FROM --platform=linux/arm64/v8  jenkins/jenkins:lts
#hier ist es https://hub.docker.com/r/jenkins/jenkins/tags
FROM  jenkins/jenkins:latest-jdk11 

# Skip setup wizard
ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"
 
# Get plugins
RUN jenkins-plugin-cli \
  --plugins \
  workflow-multibranch:latest \
  pipeline-model-definition:latest \
  pipeline-stage-view:latest \
  git:latest \
  credentials:latest \
  ssh-agent:latest
 
# Add groovy script to Jenkins hook
COPY --chown=jenkins:jenkins init.groovy.d/ /var/jenkins_home/init.groovy.d/

