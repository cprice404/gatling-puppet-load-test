#!/bin/bash

pushd jenkins-integration
source jenkins-jobs/common/scripts/initialize_ruby_env.sh

# This job sets up the following:
# - Node classified via NC to have catalog zero module

# TODO TODO TODO: this is very hard-coded and crappy right now;
#  it needs to be specific to individual jobs (though most may
#  end up using the metadata from the nodes.json files to actually
#  accomplish this)

set -x
set -e

# Setup SSH agent for SSH access to PUPPET_GATLING_MASTER_BASE_URL
eval $(ssh-agent -t 24h -s)
ssh-add ${HOME}/.ssh/id_rsa

bundle exec beaker \
        --config hosts.yaml \
        --load-path lib \
        --log-level debug \
        --no-color \
        --tests \
beaker/install/pe/60_classify_nodes.rb

# without this set +x, rvm will log 10 gigs of garbage
set +x
popd
