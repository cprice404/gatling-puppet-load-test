node {
    checkout scm
    pipeline = load 'jenkins-integration/jenkins-jobs/common/scripts/jenkins/pipeline.groovy'
}

pipeline.single_pipeline([
        job_name: 'singletest',
        server_version: [
            type: "pe",
            pe_version: "3.3.2"
        ],
        code_deploy: [
                type: "r10k",
                control_repo: "git@github.com:puppetlabs/puppetlabs-puppetserver_perf_control.git",
                basedir: "/etc/puppetlabs/code-staging/environments",
                environments: ["production"]
        ]
])
