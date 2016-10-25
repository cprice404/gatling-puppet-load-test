import groovy.io.FileType
import java.nio.file.Paths
import java.util.logging.Logger

// NOTE: these determine the default repo/branch that the seed job will
// poll to look for Jenkinsfiles.  For production they should always
// be set to the PL gplt repo's master branch.  For dev, you may want
// to change them to point to your fork or your git daemon (though you can
// accomplish the same by simply modifying the values in the Jenkins gui
// on the 'configuration' screen for the seed job.

//def git_repo = 'https://github.com/puppetlabs/gatling-puppet-load-test.git'
//def git_branch = 'master'
def git_repo = 'git://10.0.19.111/gatling-puppet-load-test'
def git_branch = 'feature/master/SERVER-1605-set-max-build-history'

String relativize(File root_dir, File f) {
    Paths.get(root_dir.absolutePath).relativize(Paths.get(f.absolutePath))
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

        relative_foofile = new File(scenarios_dir, "buildhistory-test/foo.groovy")
        def engine = new GroovyScriptEngine('.');
//        def script = new GroovyScriptEngine( '.' ).with {
            result = engine.run(relative_foofile.getAbsolutePath(), new Binding([out: out, foo: "foo"]))
            System.out.println("GOT RESULT FROM FOO SCRIPT:" + result)
            out.println("OUT PRINTER, GOT RESULT FROM FOO SCRIPT: " + result)



            Logger logger = Logger.getLogger('org.example.jobdsl')
            logger.info('Hello from a Job DSL script! RESULT:"' + result)
//        }

        job = workflowJob(job_prefix) {
            // TODO: this should be moved into the Jenkinsfile by use of
            // the 'properties' step, see https://issues.jenkins-ci.org/browse/JENKINS-32780
            parameters {
                stringParam('SUT_HOST',
                        'foo-sut.delivery.puppetlabs.net',
                        'The host/IP address of the system to use as the SUT')
                booleanParam('SKIP_SERVER_INSTALL', false, 'If checked, will skip over the PE/OSS Server Install step.  Useful if you are doing development and already have a server SUT.')
                booleanParam('SKIP_PROVISIONING', true, 'If checked, will skip over the Razor provisioning step.  Useful if you already have an SUT provisioned, e.g. via the VM Pooler.')
                booleanParam('FAKE_PARAM', true, "This param doesn't do anything!")
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
        }

        job.with {
            logRotator {
                numToKeep(3)
            }
        }
    }

}
