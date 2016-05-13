pipeline = load 'jenkins-integration/jenkins-jobs/common/scripts/pipeline.groovy'

def createPipeline(git_url, git_branch) {
    pipeline.build(git_url, git_branch)
}

return this;
