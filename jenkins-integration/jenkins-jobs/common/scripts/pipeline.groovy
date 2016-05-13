def build() {
    echo 'the pipeline, boss'
    stage '010-provision-sut'
    echo "Hi! TODO: I should be provisioning your SUT, but I'm not."
    stage '020-install-pe'
    echo "Hi! TODO: I should be installing PE on the SUT, but I'm not."
    stage '030-customize-settings'
    echo "Hi! TODO: I should be customizing PE settings on the SUT, but I'm not."
    stage '040-install-puppet-code'
    echo "Hi! TODO: I should be installing puppet code on the SUT, but I'm not."
    stage '050-file-sync'
    echo "Hi! TODO: I should be performing a file sync on the SUT, but I'm not."
    stage '060-classify-nodes'
    echo "Hi! TODO: I should be classifying nodes on SUT, but I'm not."
    stage '070-validate-classification'
    echo "Hi! TODO: I should be validating classification on your SUT, but I'm not."
    stage '080-launch-bg-script'
    echo "Hi! TODO: I should be launching background scripts on your SUT, but I'm not."
    stage '090-run-gatling-sim'
    echo "Hi! TODO: I should be running the gatling sim against your SUT, but I'm not."
    stage '100-collect-artifacts'
    echo "Hi! TODO: I should be collecting the final job artifacts, but I'm not."

}
return this;