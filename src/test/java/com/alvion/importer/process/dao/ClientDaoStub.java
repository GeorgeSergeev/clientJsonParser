package com.alvion.importer.process.dao;

import com.alvion.importer.models.Client;
import com.alvion.importer.models.ClientStatistic;
import com.alvion.importer.reader.dao.ClientDao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ClientDaoStub implements ClientDao {

	private TemporaryDatabase temporaryDatabase;

	public ClientDaoStub(TemporaryDatabase temporaryDatabase) {
		this.temporaryDatabase = temporaryDatabase;
	}

	@Override
	public Client getClient(File file) {
		return null;
	}

	@Override
	public List<Client> getClientList() {
		return new ArrayList<>();
	}

	@Override
	public boolean save(ClientStatistic clientStatistic) {
		return temporaryDatabase.save(clientStatistic);
	}
}
