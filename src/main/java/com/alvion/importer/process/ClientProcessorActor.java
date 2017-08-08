package com.alvion.importer.process;

import akka.actor.TypedProps;
import com.alvion.importer.models.Client;
import com.alvion.importer.models.ClientResult;
import com.alvion.importer.reader.dao.ClientDao;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.RuntimeDroolsException;
import org.drools.builder.*;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;


public class ClientProcessorActor implements ClientProcessor{

	private ClientDao clientDao;
	private StatelessKnowledgeSession ksession;

	private String ruleName;

	public static TypedProps<ClientProcessorActor> create(ClientDao clientDao, String ruleName) {
		return new TypedProps<>(ClientProcessor.class, () -> new ClientProcessorActor(clientDao, ruleName));
	}

	public ClientProcessorActor(ClientDao clientDao, String ruleName) throws Exception {
		this.clientDao = clientDao;
		this.ruleName = ruleName;

		KnowledgeBase kbase = readKnowledgeBase();
		ksession = kbase.newStatelessKnowledgeSession();
	}

	@Override
	public void process(Client client) {
		ClientResult resultClient = new ClientResult(client);

		ksession.execute(resultClient);
		System.out.println("result after ->" + resultClient);

		clientDao.save(resultClient.getStatistic());

	}

	private KnowledgeBase readKnowledgeBase() throws Exception {

		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		if(ruleName == null) {
			throw new RuntimeDroolsException("The rule name is udefined. Please define rule name in property file");
		}
		kbuilder.add(ResourceFactory.newClassPathResource(ruleName), ResourceType.DRL);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}
}
