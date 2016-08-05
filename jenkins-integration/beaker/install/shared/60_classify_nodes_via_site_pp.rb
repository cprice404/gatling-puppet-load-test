require 'puppet/gatling/config'

test_name "Classify Puppet agents on master"

def generate_sitepp(node_configs)
  node_configs.map do |config|
    config['classes'].
      map { |klass| "include #{klass}" }.
      insert(0, "node '#{config['certname']}' {").
      push('}').
      join("\n")
  end.join("\n").strip
end

def classify_foss_nodes(host, nodes)
  environments = on(host, puppet('config print environmentpath')).stdout.chomp
  # on old PEs (3.3 at least), this returns an empty string, so we'll hard-code
  #  the path for now.  This may need to be changed to support more versions of
  #  PE and/or OSS in the future.
  if environments == ""
    environments = '/etc/puppetlabs/puppet/environments'
  end
  nodes = group_by_environment(nodes)
  nodes.each_pair do |env, node_configs|
    manifestdir = "#{environments}/#{env}/manifests"
    manifestfile = "#{manifestdir}/site.pp"
    Beaker::Log.notify("Saving node configs for env '#{env}' to file '#{manifestfile}'")
    sitepp = generate_sitepp(node_configs)

    on(host, "mkdir -p #{manifestdir}")
    create_remote_file(host, manifestfile, sitepp)
    on(host, "chmod 644 #{manifestdir}/site.pp")
  end
end

nodes = node_configs(get_scenario_from_env())
classify_foss_nodes(master, nodes)
