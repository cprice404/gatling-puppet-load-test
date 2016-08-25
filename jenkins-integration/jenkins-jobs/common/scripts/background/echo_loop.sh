#!/usr/bin/env bash

set -e
set -x

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

COUNTER=0
while true; do
    echo "The counter is $COUNTER" >> "${DIR}/echo.txt"
    let COUNTER=COUNTER+1
    sleep 5
done
