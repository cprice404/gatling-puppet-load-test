pipeline = load 'jenkins-integration/jenkins-jobs/common/scripts/pipeline.groovy'

def createPipeline() {
    pipeline.build()
}

return this;
