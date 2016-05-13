#!/bin/bash

pushd jenkins-integration
source jenkins-jobs/common/scripts/initialize_ruby_env.sh

# This job sets up the following:
# - local keystore file that gatling can use to talk to the SUT
# - executes gatling simulation

set -x
set -e

bundle exec beaker \
        --config hosts.yaml \
        --load-path lib \
        --log-level debug \
        --no-color \
        --tests \
beaker/install/shared/configure_gatling_auth.rb


# without this set +x, rvm will log 10 gigs of garbage
set +x
popd

set -x
pushd simulation-runner
PUPPET_GATLING_MASTER_BASE_URL=https://$PUPPET_GATLING_MASTER_BASE_URL:8140 sbt run
# without this set +x, rvm will log 10 gigs of garbage
set +x
popd
