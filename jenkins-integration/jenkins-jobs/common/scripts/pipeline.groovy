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
        sh "${script_dir}/020_install_pe.sh"

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

}
return this;