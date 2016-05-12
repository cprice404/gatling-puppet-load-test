import groovy.io.FileType
import java.nio.file.Paths

def git_repo = 'git://10.0.19.111/gatling-puppet-load-test'
def git_branch = 'scratch/master/pipeline-test'

String relativize(File root_dir, File f) {
    Paths.get(root_dir.absolutePath).relativize(Paths.get(f.absolutePath))
}

dir = new File(__FILE__).parentFile.absoluteFile

def root_dir = dir
while (root_dir.name != "jenkins-integration") {
    root_dir = root_dir.parentFile
}
root_dir = root_dir.parentFile

dir.eachFileRecurse (FileType.FILES) { file ->
    if (file.name.equals("Jenkinsfile")) {
        job_prefix = file.parentFile.name
        relative_jenkinsfile = relativize(root_dir, file)
        workflowJob(job_prefix) {
            definition {
                cpsScm {
                    scm {
                        git {
                            remote {
                                url(git_repo)
                            }
                            branch(git_branch)
                        }
                    }
                    scriptPath(relative_jenkinsfile)
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
