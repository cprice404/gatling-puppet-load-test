#!/usr/bin/env bash

set -e
set -x

echo ""
echo "Setting Up Ruby Environment"
echo ""
. /usr/local/rvm/scripts/rvm
rvm use 2.1.6 || exit 1
rvm list
export GEM_SOURCE=http://rubygems.delivery.puppetlabs.net
echo -e "\n\n\n"

which ruby
which bundle