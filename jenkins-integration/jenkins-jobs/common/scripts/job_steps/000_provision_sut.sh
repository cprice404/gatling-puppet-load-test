#!/usr/bin/env bash

pushd jenkins-integration
#source jenkins-jobs/common/scripts/job_steps/initialize_ruby_env.sh

if [ -z "$SUT_HOST" ]; then
    echo "Missing required environment variable SUT_HOST"
    exit 1
fi

set -e
set -x

# TODO: ultimately we are going to need a better way to do this in the long run

case $SUT_HOST in
  puppetserver-perf-sut54.delivery.puppetlabs.net)
    RAZOR_NODE="node54"
    ;;
  puppetserver-perf-sut56.delivery.puppetlabs.net)
    RAZOR_NODE="node56"
    ;;
  puppetserver-perf-sut57.delivery.puppetlabs.net)
    RAZOR_NODE="node57"
    ;;
  *)
    echo "Unrecognized SUT_HOST: '${SUT_HOST}'!!  Don't know how to request razor reprovisioning."
    exit 1
    ;;
esac

echo "About to SSH to razor server to request node reinstall for node '${RAZOR_NODE}'"
whoami
ls -l ~/.ssh
cat ~/.ssh/id_rsa.pub
# I'm pretty sure these flags mean "super secure"
ssh -o StrictHostKeyChecking=no jenkins@boot-razor1-prod.ops.puppetlabs.net razor reinstall-node --name $RAZOR_NODE
# this first ssh to the SUT just ensures that our credentials are OK
ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no root@${SUT_HOST} true
# this line actually does the reboot, which will sever the connection and result
#  in a non-zero exit code, so we add '|| true' so the bash script won't exit.
ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no root@${SUT_HOST} reboot || true
sleep 10
ATTEMPTS=0
while [ $SUT_HOST -lt 20 ]; do
   echo "Attempting to connect to ${SUT_HOST}"
   if `ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no root@${SUT_HOST} true`; then
     break;
   else
     echo "Unable to connect; sleeping 1 min before retrying"
     sleep 60
     let ATTEMPTS=ATTEMPTS+1
   fi
done

echo "Provisioning complete"

# without this set +x, rvm will log 10 gigs of garbage
set +x
popd
