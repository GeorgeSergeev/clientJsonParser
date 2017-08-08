package com.alvion.importer.process;

import com.alvion.importer.JsonFileGenerator;
import com.alvion.importer.models.Client;
import com.alvion.importer.models.ClientStatistic;
import com.alvion.importer.models.Subscriber;
import com.alvion.importer.process.dao.TemporaryDatabase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.NONE)
public class ClientProcessorActorTest {

	@Autowired
	private ClientProcessor processor;
	@Autowired
	private TemporaryDatabase database;

	CountDownLatch latch = new CountDownLatch(1);

	@Test
	public void testProcessWithOneClientAndNullValues() throws Exception {

		List<Subscriber> subscribers = new ArrayList<>();

		Subscriber subscriber1 = new Subscriber();
		subscriber1.setId(1L);
		subscriber1.setSpent(10L);
		subscribers.add(subscriber1);

		Subscriber subscriber2 = new Subscriber();
		subscriber2.setId(2L);
		subscriber2.setSpent(12L);
		subscribers.add(subscriber2);

		Subscriber subscriber3 = new Subscriber();
		subscriber3.setId(3L);
		subscriber3.setSpent(17L);
		subscribers.add(subscriber3);

		Client client = new Client();
		client.setClientId(1L);
		client.setSubscribers(subscribers);

		processor.process(client);

		latch.await(20, TimeUnit.SECONDS);

		ClientStatistic clientStatistic = database.read(1L);
		Assert.assertEquals(new Long(1L), clientStatistic.getClientId());
		Assert.assertEquals(new Long(39L), clientStatistic.getSpentTotal());
		Assert.assertEquals(false, clientStatistic.getIsBig());
	}

	@Test
	public void testProcessWithOneClientAndMoreThea100Subscribers() throws Exception {

		Client client = JsonFileGenerator.randomizeClient(101, 110);
		client.setClientId(1L);

		processor.process(client);

		latch.await(10, TimeUnit.SECONDS);

		ClientStatistic clientStatistic = database.read(1L);
		Assert.assertEquals(true, clientStatistic.getIsBig());
	}

	@Test
	public void testProcessWithOneClientLessThen100() throws Exception {

		List<Subscriber> subscribers = new ArrayList<>();

		Subscriber subscriber1 = new Subscriber();
		subscriber1.setId(1L);
		subscriber1.setSpent(10L);
		subscribers.add(subscriber1);

		Subscriber subscriber2 = new Subscriber();
		subscriber2.setId(2L);
		subscriber2.setSpent(12L);
		subscribers.add(subscriber2);

		Subscriber subscriber3 = new Subscriber();
		subscriber3.setId(3L);
		subscriber3.setSpent(17L);
		subscribers.add(subscriber3);

		Client client = new Client();
		client.setClientId(1L);
		client.setSubscribers(subscribers);

		processor.process(client);

		latch.await(10, TimeUnit.SECONDS);

		ClientStatistic clientStatistic = database.read(1L);
		Assert.assertEquals(new Long(1L), clientStatistic.getClientId());
		Assert.assertEquals(new Long(39L), clientStatistic.getSpentTotal());
		Assert.assertEquals(false, clientStatistic.getIsBig());
	}

	@Test
	public void testProcessWithOneClientMoreThen100() throws Exception {

		List<Subscriber> subscribers = new ArrayList<>();

		Subscriber subscriber1 = new Subscriber();
		subscriber1.setId(1L);
		subscriber1.setSpent(10L);
		subscribers.add(subscriber1);

		Subscriber subscriber2 = new Subscriber();
		subscriber2.setId(2L);
		subscriber2.setSpent(30L);
		subscribers.add(subscriber2);

		Subscriber subscriber3 = new Subscriber();
		subscriber3.setId(3L);
		subscriber3.setSpent(75L);
		subscribers.add(subscriber3);

		Client client = new Client();
		client.setClientId(1L);
		client.setSubscribers(subscribers);

		processor.process(client);

		latch.await(10, TimeUnit.SECONDS);

		ClientStatistic clientStatistic = database.read(1L);
		Assert.assertEquals(new Long(1L), clientStatistic.getClientId());
		Assert.assertEquals(new Long(115L), clientStatistic.getSpentTotal());
		Assert.assertEquals(false, clientStatistic.getIsBig());
	}


	@Test
	public void testProcessWithManyClients() throws Exception {

		List<Subscriber> subscribers = new ArrayList<>();

		Subscriber subscriber1 = new Subscriber();
		subscriber1.setId(1L);
		subscriber1.setSpent(10L);
		subscribers.add(subscriber1);

		Subscriber subscriber2 = new Subscriber();
		subscriber2.setId(2L);
		subscriber2.setSpent(30L);
		subscribers.add(subscriber2);

		Subscriber subscriber3 = new Subscriber();
		subscriber3.setId(3L);
		subscriber3.setSpent(75L);
		subscribers.add(subscriber3);

		for (long i=0; i<1000; i++) {
			Client client = new Client();
			client.setClientId(i);
			client.setSubscribers(subscribers);

			processor.process(client);
		}

		latch.await(10, TimeUnit.SECONDS);

		for (long i=0; i<1000; i++) {

			ClientStatistic clientStatistic = database.read(i);
			Assert.assertEquals(new Long(i), clientStatistic.getClientId());
			Assert.assertEquals(new Long(115L), clientStatistic.getSpentTotal());
			Assert.assertEquals(false, clientStatistic.getIsBig());
		}
	}

}