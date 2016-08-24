step "Launch background scripts on SUT" do
  bg_scripts = ENV['SUT_BACKGROUND_SCRIPTS'].split("\n")
  Beaker::Log.notify("Launching #{bg_scripts.count} background scripts")
  bg_scripts.each do |s|
    Beaker::Log.notify("Launching script '#{s}'")
  end
end
