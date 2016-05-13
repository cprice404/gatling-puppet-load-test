#!/usr/bin/env bash

pushd jenkins-integration/jenkins-jobs/common/scripts
source ./initialize_ruby_env.sh
./setup_beaker_env.sh
./setup_master.sh
popd