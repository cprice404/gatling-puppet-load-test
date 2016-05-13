#!/usr/bin/env bash

pushd jenkins-integration/common/scripts
source ./initialize_ruby_env.sh
./setup_beaker_env.sh
./setup_master.sh
popd