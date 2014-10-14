package com.puppetlabs.gatling.node_simulations
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import scala.concurrent.duration._

class PE33_ConsolePerfTest extends com.puppetlabs.gatling.runner.SimulationWithScenario {

	val httpConf = http
			.baseURL("https://pemaster:8140")
			.acceptHeader("pson, b64_zlib_yaml, yaml, raw")
			.connection("close")
			.userAgentHeader("Ruby")


	val headers_3 = Map(
			"Accept" -> """pson, b64_zlib_yaml, yaml, dot, raw""",
			"Content-Type" -> """application/x-www-form-urlencoded"""
	)

	val headers_108 = Map(
			"Accept" -> """pson, yaml""",
			"Content-Type" -> """text/pson"""
	)


	val chain_0 =
		exec(http("node")
					.get("/production/node/pdb")
					.queryParam("""transaction_uuid""", """bed8079f-4032-4baa-93cf-4c93d499ee60""")
					.queryParam("""fail_on_404""", """true""")
			)
		.pause(344 milliseconds)
		.exec(http("filemeta plugins")
					.get("/production/file_metadatas/plugins")
					.queryParam("""checksum_type""", """md5""")
					.queryParam("""links""", """manage""")
					.queryParam("""recurse""", """true""")
					.queryParam("""ignore""", """.svn""")
					.queryParam("""ignore""", """CVS""")
					.queryParam("""ignore""", """.git""")
			)
		.pause(3)
		.exec(http("catalog")
					.post("/production/catalog/pdb")
					.headers(headers_3)
						.formParam("""facts_format""", """pson""")
						.formParam("""facts""", """%7B%22name%22%3A%22pdb%22%2C%22values%22%3A%7B%22architecture%22%3A%22x86_64%22%2C%22augeasversion%22%3A%221.1.0%22%2C%22kernel%22%3A%22Linux%22%2C%22blockdevice_sda_size%22%3A%228589934592%22%2C%22blockdevice_sda_vendor%22%3A%22ATA%22%2C%22blockdevice_sda_model%22%3A%22VBOX+HARDDISK%22%2C%22blockdevice_sdb_size%22%3A%221285554176%22%2C%22blockdevice_sdb_vendor%22%3A%22ATA%22%2C%22blockdevice_sdb_model%22%3A%22VBOX+HARDDISK%22%2C%22blockdevices%22%3A%22sda%2Csdb%22%2C%22domain%22%3A%22performance.delivery.puppetlabs.net%22%2C%22macaddress%22%3A%2208%3A00%3A27%3A4B%3A96%3A93%22%2C%22osfamily%22%3A%22RedHat%22%2C%22operatingsystem%22%3A%22CentOS%22%2C%22facterversion%22%3A%221.7.5%22%2C%22filesystems%22%3A%22ext4%2Ciso9660%22%2C%22fqdn%22%3A%22pdb.performance.delivery.puppetlabs.net%22%2C%22hardwareisa%22%3A%22x86_64%22%2C%22hardwaremodel%22%3A%22x86_64%22%2C%22hostname%22%3A%22pdb%22%2C%22id%22%3A%22root%22%2C%22interfaces%22%3A%22eth0%2Ceth1%2Clo%22%2C%22ipaddress_eth0%22%3A%2210.0.2.15%22%2C%22macaddress_eth0%22%3A%2208%3A00%3A27%3A4B%3A96%3A93%22%2C%22netmask_eth0%22%3A%22255.255.255.0%22%2C%22mtu_eth0%22%3A%221500%22%2C%22ipaddress_eth1%22%3A%2210.16.150.151%22%2C%22macaddress_eth1%22%3A%2208%3A00%3A27%3A8D%3ABC%3A9D%22%2C%22netmask_eth1%22%3A%22255.255.255.0%22%2C%22mtu_eth1%22%3A%221500%22%2C%22ipaddress_lo%22%3A%22127.0.0.1%22%2C%22netmask_lo%22%3A%22255.0.0.0%22%2C%22mtu_lo%22%3A%2216436%22%2C%22ipaddress%22%3A%2210.0.2.15%22%2C%22kernelmajversion%22%3A%222.6%22%2C%22kernelrelease%22%3A%222.6.32-358.23.2.el6.x86_64%22%2C%22kernelversion%22%3A%222.6.32%22%2C%22bios_vendor%22%3A%22innotek+GmbH%22%2C%22bios_version%22%3A%22VirtualBox%22%2C%22bios_release_date%22%3A%2212%2F01%2F2006%22%2C%22manufacturer%22%3A%22innotek+GmbH%22%2C%22productname%22%3A%22VirtualBox%22%2C%22serialnumber%22%3A%220%22%2C%22uuid%22%3A%22F027E4DD-1072-4F33-B5BF-9C579F317A57%22%2C%22memorysize%22%3A%221.83+GB%22%2C%22memoryfree%22%3A%221.21+GB%22%2C%22swapsize%22%3A%221.20+GB%22%2C%22swapfree%22%3A%221.20+GB%22%2C%22swapsize_mb%22%3A%221224.99%22%2C%22swapfree_mb%22%3A%221224.99%22%2C%22memorysize_mb%22%3A%221877.61%22%2C%22memoryfree_mb%22%3A%221238.38%22%2C%22memorytotal%22%3A%221.83+GB%22%2C%22netmask%22%3A%22255.255.255.0%22%2C%22network_eth0%22%3A%2210.0.2.0%22%2C%22network_eth1%22%3A%2210.16.150.0%22%2C%22network_lo%22%3A%22127.0.0.0%22%2C%22operatingsystemmajrelease%22%3A%226%22%2C%22operatingsystemrelease%22%3A%226.4%22%2C%22path%22%3A%22%2Fusr%2Flocal%2Fsbin%3A%2Fusr%2Flocal%2Fbin%3A%2Fsbin%3A%2Fbin%3A%2Fusr%2Fsbin%3A%2Fusr%2Fbin%3A%2Froot%2Fbin%22%2C%22physicalprocessorcount%22%3A%221%22%2C%22processor0%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22processorcount%22%3A%221%22%2C%22ps%22%3A%22ps+-ef%22%2C%22puppetversion%22%3A%223.6.2+%28Puppet+Enterprise+3.3.0-rc2-367-gfc8a68c%29%22%2C%22rubysitedir%22%3A%22%2Fopt%2Fpuppet%2Flib%2Fruby%2Fsite_ruby%2F1.9.1%22%2C%22rubyversion%22%3A%221.9.3%22%2C%22selinux%22%3A%22false%22%2C%22sshdsakey%22%3A%22AAAAB3NzaC1kc3MAAACBANi6HVpKBVruFlhS05%2B3RyuqToVcmZtKhtVo1yZjwNEKc0tdGYY2Lhwum%2BVnN4nWrsCHVwH9FImYtceHF7Y%2BKiWDALnhfpEsrzAq7lnmRtGw%2FNnEcWUgux1kganYgJCGO5A3sUTX1EZdTokU106hZSrkQP2IXKEhtfBJHqQb%2FLqdAAAAFQDdZ3FWOMfnuRo04%2FZvbbNhQUr8MQAAAIBTULnPAdxb4%2FPUv6d6jxDRsqF3Oq2aSCaoUUSP8fzyy9lWnFER%2BPvFQqCV2yWVuO5oHECJMZYExtEli%2FfJbo9Th7TzdGxNM1m1sqpeVeRZDf%2BX2Tp%2BNsIV18%2FK3sodatu02KsjHRbf2aGxHxsQlND2Q%2Fm9IPgwizTH7gmujGu%2BKQAAAIEA09cbKP80Q0xlLy0ql5a4jvCDLD%2Bs3L9gRud6g7VnlbcsOGBQSJ519CLO%2FBk6vLv3otx1HenVSNETcWL2j5YROvhSyVcDDqaqicxrOFV0OUfqMgI02%2FhqdsOc6Y1Fq2eYxK%2FxObBz5YVY1xhQTdyEHtI6%2FYAvxOAmsgLtBwxA8O8%3D%22%2C%22sshfp_dsa%22%3A%22SSHFP+2+1+ab7a6a1785ccfcc1568e586ee52b96edaeaad510%5CnSSHFP+2+2+76bafcaac4bb34b48d5c8b020df4f3a09a79e42a59dbb8ce2918501e0cdd7227%22%2C%22sshrsakey%22%3A%22AAAAB3NzaC1yc2EAAAABIwAAAQEA3m9UrezqX7O6QmhXFhvTLO5bcYivw%2BQhvJ7JmEzuBjDa5d9Jnf9qhSo6e7JtIODy50TUie9NW69dciWEwtKCmvmIpgwWj2rD8vgdM5ZetReFX1kwSLeprLDDjQ5xnQLoh7i%2F03%2BU3E3Q%2F97nYCIFANbRQ49XxYB4FwaByoGQoQIxFQt7G24aFjYYJIumbG0dz7pByPmmIyVAF5dKKQza9SLYhTdcH2kpDbBUbUNAuejtzgLmo%2BvlAkFdgLfqnFpczB7%2B%2FmJ0QUk4T9HWzK%2FV4WzyoF7fUBMcNtCMtCtyA4tw2f4MJz4lPaL07wMdrGiWeCtKQKLHaORFR87qu1Pc8Q%3D%3D%22%2C%22sshfp_rsa%22%3A%22SSHFP+1+1+d451a321c7e122c62c7300d13b05b2d285a1243a%5CnSSHFP+1+2+ac48b5842e4cf605f088eb796d308cd846f9624458af669e8f3f863832515cdd%22%2C%22timezone%22%3A%22UTC%22%2C%22uniqueid%22%3A%22100a9796%22%2C%22uptime%22%3A%220%3A53+hours%22%2C%22uptime_days%22%3A%220%22%2C%22uptime_hours%22%3A%220%22%2C%22uptime_seconds%22%3A%223210%22%2C%22virtual%22%3A%22virtualbox%22%2C%22is_virtual%22%3A%22true%22%2C%22concat_basedir%22%3A%22%2Fvar%2Fopt%2Flib%2Fpe-puppet%2Fconcat%22%2C%22custom_auth_conf%22%3A%22false%22%2C%22ip6tables_version%22%3A%221.4.7%22%2C%22iptables_version%22%3A%221.4.7%22%2C%22pe_build%22%3A%223.3.0-rc2-367-gfc8a68c%22%2C%22pe_postgres_default_version%22%3A%228.4%22%2C%22pe_version%22%3A%223.3.0%22%2C%22is_pe%22%3A%22true%22%2C%22pe_major_version%22%3A%223%22%2C%22pe_minor_version%22%3A%223%22%2C%22pe_patch_version%22%3A%220%22%2C%22platform_tag%22%3A%22el-6-x86_64%22%2C%22postgres_default_version%22%3A%228.4%22%2C%22puppet_vardir%22%3A%22%2Fvar%2Fopt%2Flib%2Fpe-puppet%22%2C%22root_home%22%3A%22%2Froot%22%2C%22staging_http_get%22%3A%22curl%22%2C%22clientcert%22%3A%22pdb%22%2C%22clientversion%22%3A%223.6.2+%28Puppet+Enterprise+3.3.0-rc2-367-gfc8a68c%29%22%2C%22clientnoop%22%3A%22false%22%7D%2C%22timestamp%22%3A%222014-06-24+23%3A33%3A41+%2B0000%22%2C%22expiration%22%3A%222014-06-25T00%3A03%3A41.155275409%2B00%3A00%22%7D""")
						.formParam("""transaction_uuid""", """bed8079f-4032-4baa-93cf-4c93d499ee60""")
						.formParam("""fail_on_404""", """true""")
			)
		.pause(3)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero1/catalog-zero1-impl24.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(328 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero1/catalog-zero1-impl71.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(203 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero1/catalog-zero1-impl83.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(327 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero2/catalog-zero2-impl51.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(299 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero3/catalog-zero3-impl23.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(222 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero3/catalog-zero3-impl32.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(291 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero3/catalog-zero3-impl74.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(241 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero4/catalog-zero4-impl13.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(378 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero5/catalog-zero5-impl22.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(229 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero5/catalog-zero5-impl42.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(202 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero5/catalog-zero5-impl43.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(224 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero5/catalog-zero5-impl52.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(333 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero6/catalog-zero6-impl32.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(240 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero6/catalog-zero6-impl54.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(349 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero7/catalog-zero7-impl32.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(213 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero7/catalog-zero7-impl34.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(291 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero7/catalog-zero7-impl82.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(247 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero7/catalog-zero7-impl85.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(210 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero7/catalog-zero7-impl87.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(208 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero8/catalog-zero8-impl11.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(172 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero8/catalog-zero8-impl13.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(219 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero8/catalog-zero8-impl33.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(305 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero8/catalog-zero8-impl81.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(803 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero9/catalog-zero9-impl63.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(223 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero9/catalog-zero9-impl72.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(174 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero9/catalog-zero9-impl84.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(234 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero10/catalog-zero10-impl13.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(193 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero10/catalog-zero10-impl22.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(340 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero10/catalog-zero10-impl41.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(260 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero10/catalog-zero10-impl71.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(228 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero10/catalog-zero10-impl83.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(162 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero10/catalog-zero10-impl85.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(217 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero11/catalog-zero11-impl11.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(211 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero11/catalog-zero11-impl21.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(181 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero11/catalog-zero11-impl24.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(271 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero11/catalog-zero11-impl62.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(228 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero11/catalog-zero11-impl84.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(290 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero12/catalog-zero12-impl33.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(332 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero12/catalog-zero12-impl82.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(156 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero12/catalog-zero12-impl86.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(173 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero12/catalog-zero12-impl87.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(330 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero13/catalog-zero13-impl51.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(197 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero13/catalog-zero13-impl54.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(212 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero13/catalog-zero13-impl74.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(192 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero13/catalog-zero13-impl83.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(199 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero13/catalog-zero13-impl84.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(157 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero13/catalog-zero13-impl86.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(374 milliseconds)
	val chain_1 =
		exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero14/catalog-zero14-impl72.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(378 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero15/catalog-zero15-impl62.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(171 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero15/catalog-zero15-impl72.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(160 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero15/catalog-zero15-impl73.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(307 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero16/catalog-zero16-impl33.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(351 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero16/catalog-zero16-impl83.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(190 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero16/catalog-zero16-impl85.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(213 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero17/catalog-zero17-impl13.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(351 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero17/catalog-zero17-impl87.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(206 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero18/catalog-zero18-impl14.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(199 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero18/catalog-zero18-impl23.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(164 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero18/catalog-zero18-impl24.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(202 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero18/catalog-zero18-impl32.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(327 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero18/catalog-zero18-impl84.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(219 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero19/catalog-zero19-impl12.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(214 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero19/catalog-zero19-impl31.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(237 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero19/catalog-zero19-impl53.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(219 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero19/catalog-zero19-impl54.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(254 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero19/catalog-zero19-impl83.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(158 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero19/catalog-zero19-impl86.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(267 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero20/catalog-zero20-impl24.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(219 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero20/catalog-zero20-impl42.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(283 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero20/catalog-zero20-impl71.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(219 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero20/catalog-zero20-impl73.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(277 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero21/catalog-zero21-impl32.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(205 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero21/catalog-zero21-impl41.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(280 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero21/catalog-zero21-impl82.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(197 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero21/catalog-zero21-impl86.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(163 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero21/catalog-zero21-impl87.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(279 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero22/catalog-zero22-impl34.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(271 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero22/catalog-zero22-impl52.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(286 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero23/catalog-zero23-impl21.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(314 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero23/catalog-zero23-impl62.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(349 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero24/catalog-zero24-impl52.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(274 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero24/catalog-zero24-impl83.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(227 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero25/catalog-zero25-impl13.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(218 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero25/catalog-zero25-impl22.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(171 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero25/catalog-zero25-impl24.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(271 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero25/catalog-zero25-impl51.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(398 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero26/catalog-zero26-impl54.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(214 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero26/catalog-zero26-impl64.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(237 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero26/catalog-zero26-impl86.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(278 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero27/catalog-zero27-impl33.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(176 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero27/catalog-zero27-impl41.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(284 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero27/catalog-zero27-impl63.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(281 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero28/catalog-zero28-impl13.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(219 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero28/catalog-zero28-impl33.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(182 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero28/catalog-zero28-impl42.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(213 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero28/catalog-zero28-impl51.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(224 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero28/catalog-zero28-impl72.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(230 milliseconds)
	val chain_2 =
		exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero28/catalog-zero28-impl84.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(402 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero29/catalog-zero29-impl71.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(218 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero29/catalog-zero29-impl86.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(237 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero30/catalog-zero30-impl34.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(356 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero30/catalog-zero30-impl85.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(337 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadatas/modules/pe_mcollective/plugins")
					.queryParam("""checksum_type""", """md5""")
					.queryParam("""links""", """manage""")
					.queryParam("""recurse""", """true""")
			)
		.pause(960 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/concat/concatfragments.sh")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(3)
		.exec(http("report")
					.put("/production/report/pdb")
					.headers(headers_108)
						.body(RawFileBody("PE33_ConsolePerfTest_request_108.txt"))
			)

	val scn = scenario("Scenario Name")
		.exec(
			chain_0,			chain_1,			chain_2		)

	setUp(scn.inject(atOnceUsers(1)).protocols(httpConf))
}
