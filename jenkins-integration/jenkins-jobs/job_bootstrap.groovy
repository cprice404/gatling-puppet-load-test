import groovy.io.FileType
import java.nio.file.Path
import java.nio.file.Paths

def git_repo = 'git://10.0.19.111/gatling-puppet-load-test'
def git_branch = 'scratch/master/pipeline-test'

//def dir = new File("jenkins-integration/jenkins-jobs")
//def dir = new File(".").absoluteFile

String relativize(File root_dir, File f) {
    Paths.get(root_dir.absolutePath).relativize(Paths.get(f.absolutePath))
}

//def __FILE__ = new File("/home/cprice/work/puppet-server/git/gatling-puppet-load-test/jenkins-integration/jenkins-jobs/job_bootstrap.groovy").absolutePath

println("script directory: ${new File(__FILE__).parentFile.absolutePath}")
dir = new File(__FILE__).parentFile.absoluteFile

root_dir = dir
while (root_dir.name != "jenkins-integration") {
    root_dir = root_dir.parentFile
}
root_dir = root_dir.parentFile

println "root_dir: " + root_dir
//println "relativized:" + Paths.get(root_dir.absolutePath).relativize(Paths.get(dir.absolutePath))
println "RELATIVE: " + relativize(root_dir, dir)



println "DIR: " + dir

println "CWD: " + new File(".")

dir.eachFileRecurse (FileType.FILES) { file ->
//    println "FILE: " + file
    if (file.name.equals("Jenkinsfile")) {
        println "FOUND A JANKFILE: " + file
        println "Parent dir:" + file.parentFile.name
        job_prefix = file.parentFile.name
        relative_jankfile = relativize(root_dir, file)
        println "RELATIVE JANKFILE: " + relative_jankfile
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
                    scriptPath(relative_jankfile)
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
