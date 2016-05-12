import groovy.io.FileType

def repo = 'git://10.0.19.111/gatling-puppet-load-test'
def branch = 'scratch/master/pipeline-test'

def dir = new File(".")
dir.eachFileRecurse (FileType.FILES) { file ->
//    println "FILE: " + file
    if (file.name.equals("Jenkinsfile")) {
        println "FOUND A JANKFILE: " + file
        println "Parent dir:" + file.parentFile.name
        job_prefix = file.parentFile.name
        workflowJob('thabootstrap') {
            definition {
                cpsScm {
                    scm {
                        git {
                            remote {
                                url(repo)
                            }
                            branch(branch)
                        }
                    }
                    scriptPath(file)
                }
            }
        }
    }
}
//
//list.each {
//    println it.path
//}

//println "FOOOOO!: " + dir


//workflowJob('thabootstrap') {
//    definition {
//        cpsScm {
//            scm {
//                git {
//                    remote {
//                        url('git://10.0.19.111/gatling-puppet-load-test')
//                    }
//                    branch('scratch/master/pipeline-test')
//                }
//            }
//            scriptPath('Jenkinsfile')
//        }
//    }
//}
