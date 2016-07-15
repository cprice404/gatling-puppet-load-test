import groovy.io.FileType
import java.nio.file.Paths

// NOTE: these determine the default repo/branch that the seed job will
// poll to look for Jenkinsfiles.  For production they should always
// be set to the PL gplt repo's master branch.  For dev, you may want
// to change them to point to your fork or your git daemon (though you can
// accomplish the same by simply modifying the values in the Jenkins gui
// on the 'configuration' screen for the seed job.

def git_repo = 'git://10.0.19.111/gatling-puppet-load-test'
def git_branch = 'feature/master/SERVER-328-provision-SUT'

String relativize(File root_dir, File f) {
    Paths.get(root_dir.absolutePath).relativize(Paths.get(f.absolutePath))
}

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
scenarios_dir = new File(dir, "scenarios")

scenarios_dir.eachFileRecurse (FileType.FILES) { file ->
    if (file.name.equals("Jenkinsfile")) {
        job_prefix = file.parentFile.name
        relative_jenkinsfile = relativize(root_dir, file)
        workflowJob(job_prefix) {
            // TODO: this should be moved into the Jenkinsfile by use of
            // the 'properties' step, see https://issues.jenkins-ci.org/browse/JENKINS-32780
            parameters {
                stringParam('SUT_HOST',
                        'puppetserver-perf-sut54.delivery.puppetlabs.net',
                        'The host/IP address of the system to use as the SUT')
                stringParam('PUPPET_GATLING_SIMULATION_CONFIG',
                        '../simulation-runner/config/scenarios/ops-scenario.json',
                        'The path to the gplt gatling scenario config file.')
                booleanParam('SKIP_PE_INSTALL', false, 'If checked, will skip over the PE Install step.  Useful if you are doing development and already have a PE SUT.')
                booleanParam('SKIP_PROVISIONING', true, 'If checked, will skip over the Razor provisioning step.  Useful if you already have an SUT provisioned, e.g. via the VM Pooler.')
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
    }
}
