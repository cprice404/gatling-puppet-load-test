def build() {
    echo 'the pipeline, boss'
    stage '010-provision-sut'
    stage '020-install-pe'
    stage '030-customize-settings'
    stage '040-install-puppet-code'
    stage '050-file-sync'
    stage '060-classify-nodes'
    stage '070-validate-classification'
    stage '080-launch-bg-script'
    stage '090-run-gatling-sim'
    stage '100-collect-artifacts'
}
return this;