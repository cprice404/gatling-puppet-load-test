#!/bin/bash

set -x
set -e

bundle exec beaker \
        --config hosts.yaml \
        --load-path lib \
        --log-level debug \
        --no-color \
        --tests \
beaker/install/shared/configure_gatling_auth.rb

pushd ../simulation-runner
PUPPET_GATLING_MASTER_BASE_URL=https://$PUPPET_GATLING_MASTER_BASE_URL:8140 sbt run
popd

