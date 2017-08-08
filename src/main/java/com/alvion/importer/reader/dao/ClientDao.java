package com.alvion.importer.reader.dao;

import com.alvion.importer.models.Client;
import com.alvion.importer.models.ClientStatistic;

import java.io.File;
import java.util.List;

public interface ClientDao {

	Client getClient(File file);

	List<Client> getClientList();

	boolean save(ClientStatistic clientStatistic);
}
