package com.alvion.importer;

import akka.actor.ActorSystem;
import akka.actor.TypedActor;
import com.alvion.importer.process.ClientProcessor;
import com.alvion.importer.process.ClientProcessorActor;
import com.alvion.importer.reader.dao.ClientDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ImporterApplication {
    private static final Logger logger = LoggerFactory.getLogger(ImporterApplication.class);

	@Value("${client.rule.name}")
	String ruleName;

	@Autowired
	ClientProcessor processor;

	@Autowired
	ClientDao dao;

    @Bean
    ActorSystem actorSystem() {
        return ActorSystem.create("AkkaClientProcessing");
    }

    @Bean
    ClientProcessor clientProcessor(ActorSystem system, ClientDao clientDao) {
        return TypedActor.get(system).typedActorOf(ClientProcessorActor.create(clientDao, ruleName), "clientProcessor");
    }

    public static void main(String[] args) {
        SpringApplication.run(ImporterApplication.class, args);
    }
}