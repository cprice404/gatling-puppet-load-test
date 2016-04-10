step "Hack hostname into /etc/hosts" do
  # TODO: this should be able to go away once we get DNS working on the SUTs
  on(master, "echo '#{master['ip']}\t#{master.node_name}' >> /etc/hosts")
end