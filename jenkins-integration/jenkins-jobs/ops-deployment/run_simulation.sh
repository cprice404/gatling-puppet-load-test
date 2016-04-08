#!/bin/bash

set -x
set -e

pushd ../simulation-runner
PUPPET_GATLING_MASTER_BASE_URL=https://#{$PUPPET_GATLING_MASTER_BASE_URL}:8140 sbt run
popd

