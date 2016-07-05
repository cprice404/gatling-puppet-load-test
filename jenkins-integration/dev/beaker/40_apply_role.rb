test_name "Apply puppet role for driver node in dev env"

step "Apply role on dev machine" do
  # TODO: docs here about how to set environment for dev
  on(jenkins, puppet("apply", "--environment", "maint_master_add_gatling_plugin", "-e", "'include ::puppetserver_perf_driver::role::puppetserver::perf::driver::dev'"))
end
