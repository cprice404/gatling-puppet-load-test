require 'json'

test_name 'Configure settings on SUT'

def get_puppet_settings_from_env()
  ENV['PUPPET_GATLING_PUPPET_SETTINGS']
end

# def update_service_config(host, source_path, service_name)
#   scp_to(host, source_path, service_config_name(service_name))
#   on(host, "chmod 644 #{service_config_name(service_name)}")
# end

puppet_settings = JSON.parse(get_puppet_settings_from_env())
Beaker::Log.notify("TODO: INITIALIZE PUPPET SETTINGS to: #{puppet_settings}")
