package br.com.ritcher.server.lib.db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@Component
public class DatabaseUpdate implements CommandLineRunner {

	@Autowired
	DDLDatabaseUpdate databaseUpdate;
	

	public void run(String... args) throws Exception {
		log.info("EXECUTING: DatabaseUpdate");
		databaseUpdate.update();
	}
	
	
}