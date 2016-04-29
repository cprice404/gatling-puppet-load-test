#!/usr/bin/env bash

set -e
set -x

which rvm
which ruby
which bundle

bundle install --path vendor/bundle
