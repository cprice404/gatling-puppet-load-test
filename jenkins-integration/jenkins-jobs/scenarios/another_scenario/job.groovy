pipeline = load 'jenkins-integration/jenkins-jobs/common/scripts/pipeline.groovy'

def createPipeline(git_url, git_branch,  job_name) {
    pipeline.build(git_url, git_branch, job_name)
}

return this;
