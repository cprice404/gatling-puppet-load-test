//def build(git_url, git_branch, job_name) {

def step000_provision_sut() {
    echo "Hi from new step000 method! TODO: I should be provisioning your SUT, but I'm not."
}


def build(job_name) {
    def script_dir = "./jenkins-integration/jenkins-jobs/common/scripts"

    node {
        checkout scm
//        git url: git_url,
//                branch: git_branch

        SKIP_PE_INSTALL = (SKIP_PE_INSTALL == "true")

        stage '000-provision-sut'
        step000_provision_sut()

//        step([$class: 'GatlingBuildAction'])

        stage '010-setup-beaker'
        withEnv(["SUT_HOST=${SUT_HOST}"]) {
            sh "${script_dir}/010_setup_beaker.sh"
        }

        stage '020-install-pe'
        echo "SKIP PE INSTALL?: ${SKIP_PE_INSTALL} (${SKIP_PE_INSTALL.class})"
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
        sh "${script_dir}/050_file_sync.sh"

        stage '060-classify-nodes'
        withEnv(["PUPPET_GATLING_SIMULATION_CONFIG=${PUPPET_GATLING_SIMULATION_CONFIG}"]) {
            sh "${script_dir}/060_classify_nodes.sh"
        }

        stage '070-validate-classification'
        echo "Hi! TODO: I should be validating classification on your SUT, but I'm not."

        stage '080-launch-bg-script'
        echo "Hi! TODO: I should be launching background scripts on your SUT, but I'm not."

        stage '090-run-gatling-sim'
        withEnv(["PUPPET_GATLING_SIMULATION_CONFIG=${PUPPET_GATLING_SIMULATION_CONFIG}",
                 "PUPPET_GATLING_SIMULATION_ID=${job_name}"]) {
            sh "${script_dir}/090_run_simulation.sh"
        }

        stage '100-collect-artifacts'
        gatlingArchive()
        echo "Hi! TODO: I should be collecting the final job artifacts, but I'm not."
//        step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
//        step([$class: 'GatlingBuildAction'])

    }

}
return this;
