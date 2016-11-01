out.println("EXECUTING JOBDSL.GROOVY FOR OSS LATEST")

job.with {
    triggers {
        // This should run the job at a semi-random time between 9:00 and 10:59PM,
        //  on Mondays.
        scm('H H(21-22) * * 1')
    }
//    parameters {
//        stringParam('SUT_HOST',
//                // TODO: eventually we need to come up with a better solution for
//                // managing the available SUTs to make sure that we don't try to
//                // use one of them for two jobs at the same time.  That will probably
//                // involve getting rid of the build parameter entirely, and using
//                // a jenkins plugin to request one and lock it during the run.
//                'puppetserver-perf-sut54.delivery.puppetlabs.net')
//        booleanParam('SKIP_PROVISIONING', false)
//    }
    configure { Node project ->
//        out.println("EXECUTING JOB CONFIGURE; project: ${project}")
        out.println("EXECUTING JOB CONFIGURE")

        Node node = project / 'properties' / 'hudson.model.ParametersDefinitionProperty' / 'parameterDefinitions'
        List children = node.children().collect()
        out.println("Found children: ${children.size()}")
        children.each { child ->
//            out.println("CHILD CLASS: ${child.getClass()}")
//            out.println("CHILD NAME: ${child.name()}")
//            out.println("REMOVING CHILD NODE: ${child.value().size()}")
            def my_name = child.get("name")
            def my_defaultValue = child.get("defaultValue")
            def my_name_value = my_name.value()
            out.println("FOUND NAME NODE: ${my_name_value}")
            out.println("FOUND DEFAULTVALUE NODE: ${my_defaultValue}")
//            child.value().each { nested ->
//                out.println("nested node: ${nested} (name: ${nested.name()}) (${nested.getClass()})")
//            }
            node.remove(child)
            out.println("hi! []")
        }
//        context.buildParameterNodes.values().each {
//            node << it
//        }
    }
}