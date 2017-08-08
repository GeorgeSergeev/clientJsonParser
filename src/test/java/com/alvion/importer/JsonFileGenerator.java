package com.alvion.importer;

import com.alvion.importer.models.Client;
import com.alvion.importer.models.Subscriber;
import com.alvion.importer.reader.utils.FileUtil;
import com.google.gson.Gson;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertTrue;

public class JsonFileGenerator {
	static final Logger LOG = LoggerFactory.getLogger(JsonFileGenerator.class);

    private final static int delay = 1000;
    private final static  int count = 100;
    private final static  int minSubscr = 1;
    private final static  int maxSubscr = 10;
    private final static String path = "src/main/resources/inbox";

	@Test
	public void test() throws InterruptedException, IOException {
	    LOG.info("Creating files started");
        for (int i = 0; i < count; i++) {
            Client client = randomizeClient();
            LOG.info(client.toString());
            Thread.sleep(delay);
            FileUtil.saveToFile(path, String.valueOf(client.getClientId()), objToGson(client));
        }
	}

    private String objToGson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static Client randomizeClient() {
        Client client = new Client();
        client.setClientId(randomNum(0, 99999));
        long subscr = randomNum(minSubscr, maxSubscr);
        List<Subscriber> subscribers = new ArrayList<>();
        for (int i = 0; i < subscr; i++) {
            subscribers.add(randomizeSubscruber());
        }
        client.setSubscribers(subscribers);
        return client;
    }

    public static Client randomizeClient(int minSubscr, int maxSubscr) {
        Client client = new Client();
        client.setClientId(randomNum(0, 99999));
        long subscr = randomNum(minSubscr, maxSubscr);
        List<Subscriber> subscribers = new ArrayList<>();
        for (int i = 0; i < subscr; i++) {
            subscribers.add(randomizeSubscruber());
        }
        client.setSubscribers(subscribers);
        return client;
    }

    private static Subscriber randomizeSubscruber() {
        Subscriber subscriber = new Subscriber();
        subscriber.setId(randomNum(0, 99999));
        subscriber.setSpent(randomNum(0, 999));
        return subscriber;
    }

    private static long randomNum(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }
}