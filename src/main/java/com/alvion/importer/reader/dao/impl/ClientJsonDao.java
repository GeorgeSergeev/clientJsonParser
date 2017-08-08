package com.alvion.importer.reader.dao.impl;

import com.alvion.importer.models.Client;
import com.alvion.importer.models.ClientStatistic;
import com.alvion.importer.reader.dao.ClientDao;
import com.alvion.importer.reader.utils.FileUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientJsonDao implements ClientDao {

    private static final Logger logger = LoggerFactory.getLogger(ClientJsonDao.class);

    @Value("${client.input.folder}")
    private String inputFolder;
    @Value("${client.output.folder}")
    private String outputFolder;

    public Client getClient(File file) {
        return getObjectJson(file);
    }

    @Override
    public List<Client> getClientList() {
        List<Client> clients = new ArrayList<>();
        for (File file : FileUtil.getFiles(inputFolder)) {
            Client client = getObjectJson(file);
            if(client != null) {
                clients.add(client);
            }
        }
        return clients;
    }

    @Override
    public boolean save(ClientStatistic clientStatistic) {
        String name = String.valueOf(clientStatistic.getClientId());
        Gson gson = new Gson();
        String json = gson.toJson(clientStatistic);
        try {
            FileUtil.saveToFile(outputFolder, name, json);
            return true;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    private Client getObjectJson(File file) {
        Gson gson = new Gson();

        try (FileReader fileReader = new FileReader(file); JsonReader reader = new JsonReader(fileReader)) {
            return gson.fromJson(reader, Client.class);
        } catch (IOException | JsonSyntaxException e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }
}
