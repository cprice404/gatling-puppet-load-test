import groovy.io.FileType

def git_repo = 'git://10.0.19.111/gatling-puppet-load-test'
def git_branch = 'scratch/master/pipeline-test'

//def dir = new File("jenkins-integration/jenkins-jobs")
//def dir = new File(".").absoluteFile

println("script directory: ${new File(__FILE__).parentFile.absolutePath}")
dir = new File(__FILE__).parentFile.absolutePath

println "DIR: " + dir

println "CWD: " + new File(".")

dir.absoluteFile.eachFileRecurse (FileType.FILES) { file ->
////    println "FILE: " + file
    if (file.name.equals("Jenkinsfile")) {
        println "FOUND A JANKFILE: " + file
        println "Parent dir:" + file.parentFile.name
//        job_prefix = file.parentFile.name
////        workflowJob('thabootstrap') {
////            definition {
////                cpsScm {
////                    scm {
////                        git {
////                            remote {
////                                url(git_repo)
////                            }
////                            branch(git_branch)
////                        }
////                    }
////                    scriptPath(file.absolutePath)
////                }
////            }
////        }
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
