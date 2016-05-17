#!/usr/bin/env bash

pushd simulation-runner
echo "sup, yo"
PUPPET_GATLING_SIMULATION_CONFIG=./config/scenarios/ops-scenario.json \
   PUPPET_GATLING_SIMULATION_ID=foo \
   PUPPET_GATLING_MASTER_BASE_URL=http://localhost:66666 \
   sbt run
PUPPET_GATLING_SIMULATION_CONFIG=./config/scenarios/ops-scenario.json \
   PUPPET_GATLING_SIMULATION_ID=bar \
   PUPPET_GATLING_MASTER_BASE_URL=http://localhost:66666 \
   sbt run
popd
