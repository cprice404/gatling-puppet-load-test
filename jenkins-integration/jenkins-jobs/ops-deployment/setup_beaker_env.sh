#!/usr/bin/env bash

set -e
set -x

which ruby
which bundle
which rvm

bundle install --path vendor/bundle
