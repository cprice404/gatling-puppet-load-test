#!/usr/bin/env bash

set -e
set -x

COUNTER=0
while true; do
    echo "The counter is $COUNTER" >> echo.txt
    let COUNTER=COUNTER+1
    sleep 5
done
