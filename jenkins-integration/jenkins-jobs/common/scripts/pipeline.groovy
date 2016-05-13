def build(git_url, git_branch) {
    def script_dir = "./jenkins-integration/jenkins-jobs/common/scripts"

    node {
        git url: git_url,
                branch: git_branch

        stage '000-provision-sut'
        echo "Hi! TODO: I should be provisioning your SUT, but I'm not."

        stage '010-setup-beaker'
        withEnv(["SUT_HOST=${SUT_HOST}"]) {
            sh "${script_dir}/010_setup_beaker.sh"
        }

        stage '020-install-pe'
        if (SKIP_PE_INSTALL) {
            echo "Skipping PE install because SKIP_PE_INSTALL is set."
        } else {
            sh "${script_dir}/020_install_pe.sh"
        }

        stage '030-customize-settings'
        echo "Hi! TODO: I should be customizing PE settings on the SUT, but I'm not."

        stage '040-install-puppet-code'
        sh "${script_dir}/040_install_puppet_code.sh"

        stage '050-file-sync'
        #sh "${script_dir}/050_file_sync.sh"

        stage '060-classify-nodes'
        sh "${script_dir}/060_classify_nodes.sh"

        stage '070-validate-classification'
        echo "Hi! TODO: I should be validating classification on your SUT, but I'm not."

        stage '080-launch-bg-script'
        echo "Hi! TODO: I should be launching background scripts on your SUT, but I'm not."

        stage '090-run-gatling-sim'
        echo "Hi! TODO: I should be running the gatling sim against your SUT, but I'm not."

        stage '100-collect-artifacts'
        echo "Hi! TODO: I should be collecting the final job artifacts, but I'm not."
    }

}
return this;