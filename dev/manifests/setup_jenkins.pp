#class { 'jenkins':
#  # Adds exception to firewall for jenkins
#  configure_firewall => true,
#  version => '1.642-1.1'
#}
#
## Loading the git plugin fails silently if the git-client plugin
## isn't installed along side it.
#jenkins::plugin { 'git-client': }
#
#jenkins::plugin { 'scm-api': }
#
#jenkins::plugin { 'git': }
#
#jenkins::plugin { 'sbt': }
