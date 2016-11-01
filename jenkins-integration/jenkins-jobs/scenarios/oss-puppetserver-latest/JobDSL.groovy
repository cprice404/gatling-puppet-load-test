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
        Node node = project / 'properties' / 'hudson.model.ParametersDefinitionProperty' / 'parameterDefinitions'
        List children = node.children().collect()
        children.each { child ->
            out.println("REMOVING CHILD NODE: ${child}")
            node.remove(child)
        }
//        context.buildParameterNodes.values().each {
//            node << it
//        }
    }
}