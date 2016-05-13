import groovy.io.FileType
import java.nio.file.Paths

def git_repo = 'git://10.32.128.152/gatling-puppet-load-test'
def git_branch = 'scratch/master/pipeline-test'

String relativize(File root_dir, File f) {
    Paths.get(root_dir.absolutePath).relativize(Paths.get(f.absolutePath))
}

def createScript(git_repo, git_branch, job_script) {
    """
def job =  node {
    git url: '${git_repo}', branch: '${git_branch}'
    load '${job_script}'
}
job.createPipeline('${git_repo}', '${git_branch}')
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
    } else if (file.name.equals("job.groovy")) {
        job_prefix = file.parentFile.name
        relative_script = relativize(root_dir, file)
        workflowJob(job_prefix + "2") {
            definition {
                cps {
                    script(createScript(git_repo, git_branch, relative_script))
                }
            }
        }
    }
}
