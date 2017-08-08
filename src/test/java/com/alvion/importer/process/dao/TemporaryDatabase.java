package com.alvion.importer.process.dao;

import com.alvion.importer.models.ClientStatistic;

import java.util.HashMap;
import java.util.Map;


public class TemporaryDatabase {

	private Map<Long, ClientStatistic> storage = new HashMap<>();

	public boolean save(ClientStatistic statistic) {
		storage.put(statistic.getClientId(), statistic);
		return true;
	}

	public ClientStatistic read(Long id) {
		return storage.get(id);
	}
}
