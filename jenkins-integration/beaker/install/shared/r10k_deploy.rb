require 'puppet/gatling/config'

test_name "Deploy r10k control repository"

R10K_DIR = '/opt/puppetlabs/scratch/perf_testing/r10k'
R10K_CONFIG_PATH = "#{R10K_DIR}/r10k.yaml"

## TODO This probably needs more work to finish hooking up everything that
##      comes in the control repository, like a hiera.yaml config file.
##
##      Also, r10k will manage the entire environment directory, which means
##      previous gatling installation steps (e.g. 50_install_modules.rb) may
##      be overridden. For example, any modules defined in the JSON node files
##      that aren't defined in the r10k control repo will be removed.

def install_r10k(host)
  gem = '/opt/puppetlabs/puppet/bin/gem'
  on(host, "#{gem} install r10k --no-document")
end

def create_r10k_config(host, r10k_config)

  on(host, "mkdir -p #{R10K_DIR}")

  cachedir = "#{R10K_DIR}/cache"

  r10k_config_contents = <<EOS
:cachedir: '#{cachedir}'

:sources:
  :perf-test:
    remote: '#{r10k_config[:control_repo]}'
    basedir: '#{r10k_config[:basedir]}'
EOS

  create_remote_file(host, R10K_CONFIG_PATH, r10k_config_contents)
end

def run_r10k_deploy(host, r10k_config)
  r10k = '/opt/puppetlabs/puppet/bin/r10k'
  r10k_config[:environments].each do |env|
    on(host, "#{r10k} deploy environment #{env} -p -v debug -c #{R10K_CONFIG_PATH}")
  end
end

r10k_config = get_r10k_config_from_env()
if r10k_config
  install_r10k(master)
  create_r10k_config(master, r10k_config)
  run_r10k_deploy(master, r10k_config)
else
  raise "No R10K config found in environment!"
end
