import groovy.io.FileType
import java.nio.file.Paths

def git_repo = 'git://10.32.128.152/gatling-puppet-load-test'
def git_branch = 'scratch/master/pipeline-test'

String relativize(File root_dir, File f) {
    Paths.get(root_dir.absolutePath).relativize(Paths.get(f.absolutePath))
}
//
//def createScript(git_repo, git_branch, job_name, job_script) {
//    """
//def job =  node {
//    git url: '${git_repo}', branch: '${git_branch}'
//    load '${job_script}'
//}
//job.createPipeline('${git_repo}', '${git_branch}', '${job_name}')
//"""

def createScript(job_name, job_script) {
    """
def job =  node {
    checkout scm
    load '${job_script}'
}
job.createPipeline('${job_name}')
"""
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
            parameters {
                stringParam('SUT_HOST',
                        'puppetserver-perf-sut54.delivery.puppetlabs.net',
                        'The host/IP address of the system to use as the SUT')
                stringParam('PUPPET_GATLING_SIMULATION_CONFIG',
                        '../simulation-runner/config/scenarios/ops-scenario.json',
                        'The path to the gplt gatling scenario config file.')
                booleanParam('SKIP_PE_INSTALL', false, 'If checked, will skip over the PE Install step.  Useful if you are doing development and already have a PE SUT.')
            }
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
            publishers {
                archiveArtifacts('build/test-output/**/*.html')
            }
        }
//    } else if (file.name.equals("job.groovy")) {
//        job_prefix = file.parentFile.name
//        relative_script = relativize(root_dir, file)
//        workflowJob(job_prefix + "2") {
//            parameters {
//                stringParam('SUT_HOST',
//                        'puppetserver-perf-sut54.delivery.puppetlabs.net',
//                        'The host/IP address of the system to use as the SUT')
//                stringParam('PUPPET_GATLING_SIMULATION_CONFIG',
//                        '../simulation-runner/config/scenarios/ops-scenario.json',
//                        'The path to the gplt gatling scenario config file.')
//                booleanParam('SKIP_PE_INSTALL', false, 'If checked, will skip over the PE Install step.  Useful if you are doing development and already have a PE SUT.')
//            }
//            definition {
//                cps {
////                    script(createScript(git_repo, git_branch, job_prefix, relative_script))
//                    script(createScript(job_prefix, relative_script))
//                }
//            }
//        }
    }
}
