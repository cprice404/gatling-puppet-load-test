workflowJob('thabootstrap') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('git://10.0.19.111/gatling-puppet-load-test')
                    }
                    branch('scratch/master/pipeline-test')
                }
            }
            scriptPath('Jenkinsfile')
        }
    }
}
