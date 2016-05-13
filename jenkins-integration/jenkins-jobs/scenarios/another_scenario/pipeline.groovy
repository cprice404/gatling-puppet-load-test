def hello = load 'jenkins-integration/jenkins-jobs/common/scripts/hello.groovy'

def build() {
    hello.sayHello()
}

return this;
