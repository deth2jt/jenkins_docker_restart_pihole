#!/usr/bin/env groovy

import jenkins.model.*
jenkins = Jenkins.getInstance()
globalNodeProperties = jenkins.getGlobalNodeProperties()
envVarsNodePropertyList = globalNodeProperties.getAll(hudson.slaves.EnvironmentVariablesNodeProperty.class)


envVars = null

if ( envVarsNodePropertyList == null || envVarsNodePropertyList.size() == 0 ) {
  newEnvVarsNodeProperty = new hudson.slaves.EnvironmentVariablesNodeProperty();
  globalNodeProperties.add(newEnvVarsNodeProperty)
  envVars = newEnvVarsNodeProperty.getEnvVars()
} else {
  envVars = envVarsNodePropertyList.get(0).getEnvVars()

}

envVars.put("HNAME", "pi.hole")
envVars.put("DOCKERNAME", "pihole")
envVars.put("DOCKERNAME2", "hello-world")
envVars.put("SIP", "192.168.1.169")
envVars.put("SIP2", "192.168.53.153")
jenkins.save()
