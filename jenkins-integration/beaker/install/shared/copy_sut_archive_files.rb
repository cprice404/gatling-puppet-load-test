require 'fileutils'

step "Copy archive files from SUT" do
  archive_files = ENV['SUT_ARCHIVE_FILES'].split("\n")
  Beaker::Log.notify("Copying #{archive_files.count} archive files from SUT")

  FileUtils.mkdir_p("./sut_archive_files")

  archive_files.each do |s|
    script_filename = File.basename(s)
    # remote_path = File.join(master_tempdir, script_filename)


    Beaker::Log.notify("Copying archive file '#{s}'")
    scp_from(master, s, "./sut_archive_files")
    # result = on(master, "#{remote_path} > #{master_tempdir}/stdout.log 2> #{master_tempdir}/stderr.log & echo $!")
    # Beaker::Log.notify("GOT RESULT FROM SCRIPT EXECUTION: '#{result}'")
    # Beaker::Log.notify("\tEXIT CODE: '#{result.exit_code}'")
    # Beaker::Log.notify("\tSTDOUT: '#{result.stdout}'")
    # Beaker::Log.notify("\tSTDERR: '#{result.stderr}'")
    # pid = result.stdout.chomp
    # pids[s] = pid
  end

  # Beaker::Log.notify("All bg scripts launched; saving pids to bg_pids.json")
  # File.open("bg_pids.json", "w") do |f|
  #   f.write(JSON.pretty_generate(pids))
  # end
  # Beaker::Log.notify(File.read("bg_pids.json"))
end
