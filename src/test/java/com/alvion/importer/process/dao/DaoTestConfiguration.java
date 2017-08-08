package com.alvion.importer.process.dao;

import com.alvion.importer.reader.dao.ClientDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;


@Profile("test")
@Configuration
public class DaoTestConfiguration {

	@Bean
	public TemporaryDatabase temporaryDatabase() {
		return new TemporaryDatabase();
	}

	@Bean
	@Primary
	public ClientDao clientDao(TemporaryDatabase temporaryDatabase) {
		return new ClientDaoStub(temporaryDatabase);
	}

}
