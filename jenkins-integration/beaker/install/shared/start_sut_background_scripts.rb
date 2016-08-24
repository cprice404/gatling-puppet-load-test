step "Launch background scripts on SUT" do
  bg_scripts = ENV['SUT_BACKGROUND_SCRIPTS'].split("\n")
  Beaker::Log.notify("Launching #{bg_scripts.count} background scripts")
  master_tempdir = master.tmpdir('gplt_bg_scripts')
  bg_scripts.each do |s|
    script_filename = File.basename(s)
    remote_path = File.join(master_tempdir, script_filename)
    Beaker::Log.notify("Launching script '#{s}'")
    scp_to(master, s, master_tempdir)
    on(master, "chmod 644 #{remote_path}")
    on(master, "#{remote_path} &")
  end
end
