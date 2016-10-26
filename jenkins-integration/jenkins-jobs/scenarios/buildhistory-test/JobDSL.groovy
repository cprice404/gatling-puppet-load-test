out.println("HELLO FROM JOBDSL.GROOVY!")
job.with {
    logRotator {
        numToKeep(3)
    }
}