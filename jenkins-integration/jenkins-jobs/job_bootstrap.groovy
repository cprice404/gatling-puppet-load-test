import groovy.io.FileType

def list = []

def dir = new File("path_to_parent_dir")
dir.eachFileRecurse (FileType.FILES) { file ->
    list << file
}

list.each {
    println it.path
}

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
