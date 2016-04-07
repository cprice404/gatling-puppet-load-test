#!/bin/bash

set -x
set -e

PUPPET_GATLING_MASTER_BASE_URL=https://#{$PUPPET_GATLING_MASTER_BASE_URL}:8140 sbt run

