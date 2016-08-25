#!/usr/bin/env bash

set -x

while true ; do
  curl -sS -w "\n" -k https://localhost:8140/status/v1/services?level=debug >> /var/log/puppetlabs/puppetserver/metrics.json
  sleep 10
done
