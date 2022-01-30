import jenkins.model.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import com.cloudbees.plugins.credentials.domains.*;
import com.cloudbees.plugins.credentials.*;


// Read our values into strings from the volume mount
privKeyText = new File('/tmp/ssh-key.priv').text.trim()
passPhraseText = ""
//below value does play a part, much
sshUserText = "pi"

// Get a handle on our Jenkins instance
def jenkins = Jenkins.getInstance()

// Define the security domain. We're making these global but they can also
// be configured in a more restrictive manner. More on that later
def domain = Domain.global()

// Get our existing Credentials Store
def store = jenkins.getExtensionList(
  'com.cloudbees.plugins.credentials.SystemCredentialsProvider'
  )[0].getStore()

// Create a new BasicSSHUserPrivateKey object with our values
sshKey = new BasicSSHUserPrivateKey(
  CredentialsScope.GLOBAL,
  "ssh-key-use-it",
  sshUserText,
  new BasicSSHUserPrivateKey.DirectEntryPrivateKeySource(privKeyText),
  passPhraseText,
  "PiHole User SSH Creds"
)

// Add the new object to the credentials store
store.addCredentials(domain, sshKey)
