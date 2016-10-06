## gatling-puppet-load-test Jenkinsfile syntax

So, you want to create a new perf test, and you need to build a new Ja^Henkinsfile?  You've come to the right place.

The easiest thing to do is just to create a directory for your new scenario, copy one of the existing Jenkinsfiles,
and modify it.  The [single-pass-scenario](./scenarios/single-pass-scenario/Jenkinsfile) is probably the most basic example.
The most advanced example we have at the time of this writing is probably the [couch-static-catalogs](./couch-static-catalogs/Jenkinsfile)
test.

At the end of the day, though, these files all follow a pretty simple pattern:

1. Check out the g-p-l-t repo so that we can load groovy code from it.
2. Load the library code from [pipeline.groovy](./common/scripts/jenkins/pipeline.groovy)
3. Create one or more data structures (simple groovy map objects) describing the PE/Puppet Server instance you wish to
   test
4. Call either the 'single-pipeline' or 'multipass-pipeline' methods from the pipeline library code, passing in your
   PE / Puppet Server configuration.

### Loading the g-p-l-t pipeline library

Every Jenkinsfile will start with a stanza that looks like this:

```groovy
node {
    checkout scm
    pipeline = load 'jenkins-integration/jenkins-jobs/common/scripts/jenkins/pipeline.groovy'
}
```

The `node` block just ensures that Jenkins gives us a dedicated executor node to run the step on.  The `checkout scm`
basically just says to make sure we have a local working copy of the VCS repo that relates to this job; it'll clone one
if we don't have one already, e.g. if this part of the job is running on a slave node that is different from where the
job was launched.

The `pipeline = load` block just causes the groovy code from that library file to be loaded, and the result is made
available to us as a local variable called `pipeline`.  We can now call either the `single-pipeline` or `multipass-pipeline`
methods on that object, passing in a map describing the PE/Puppet Server configuration, and the library will handle
the rest of the work of setting things up and executing the perf test.

NOTE: The `pipeline.groovy` script currently contains a bunch of raw method definitions.  I would very much like to turn
it into a proper groovy Class in the future, but haven't quite had time to figure out the mechanics of that.

### The job data structure

The most interesting / important part of these Jenkinsfiles is the data structure that describes the PE/Puppet Server
configuration and the perf test to run.  Here is an example of one of those data structures.  (Several of the fields in
this map are optional, but this example excercises all of the fields that are currently available.)

```groovy
   [job_name: "pe-couch-no-static-250",
    gatling_simulation_config: "../simulation-runner/config/scenarios/pe-couch-medium-no-static-catalogs-250-2-hours.json",
    server_version: [
             type: "pe",
             pe_version: "2016.2.0"
    ],
    code_deploy: [
             type: "r10k",
             control_repo: "git@github.com:puppetlabs/puppetlabs-puppetserver_perf_control.git",
             basedir: "/etc/puppetlabs/code-staging/environments",
             environments: ["production"],
             hiera_config_source_file: "/etc/puppetlabs/code-staging/environments/production/root_files/hiera.yaml"
    ],
    server_java_args: "-Xms12g -Xmx12g",
    puppet_settings: [
             master: [
                     "static_catalogs": "false"
             ]
    ],
    background_scripts: [
             "./jenkins-jobs/common/scripts/background/curl-server-metrics-loop.sh"
    ],
    archive_sut_files: [
             "/var/log/puppetlabs/puppetserver/metrics.json"
    ]
   ]
```

Here's some info about each of these sections:

* `job_name`: arbitrary string that will be used in a few places in the Jenkins UI to represent this job.

TODO TODO TODO