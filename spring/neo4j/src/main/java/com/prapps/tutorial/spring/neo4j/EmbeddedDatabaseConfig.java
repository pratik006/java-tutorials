package com.prapps.tutorial.spring.neo4j;

import java.io.File;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;

//@Configuration
@EnableNeo4jRepositories("com.prapps.tutorial.spring.neo4j.repo")
public class EmbeddedDatabaseConfig extends Neo4jConfiguration{
	
	public EmbeddedDatabaseConfig() {
		//setBasePackage("com.prapps.tutorial.spring.neo4j.persistence");
	}
	
	/*public static GraphDatabaseService getConnection() {
		GraphDatabaseService graphDB = new RestGraphDatabase(
				"http://trainapp.sb10.stations.graphenedb.com:24789/db/data/",
				"trainapp", "fNbzH91De5f0KgQl4rG3");

		registerShutdownHook(graphDB);

		return graphDB;
	}*/
	
	@Bean
	public GraphDatabaseService getLocalConnection() {
		GraphDatabaseService graphDB = new GraphDatabaseFactory().newEmbeddedDatabase( new File("target/db1") );
		//registerShutdownHook(graphDB);
		return graphDB;
	}

	private static void registerShutdownHook(final GraphDatabaseService graphDb) {
		// Registers a shutdown hook for the Neo4j instance so that it
		// shuts down nicely when the VM exits (even if you "Ctrl-C" the
		// running application).

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				graphDb.shutdown();
			}
		});
	}

	public static void main(String[] args) {
		GraphDatabaseService database = new EmbeddedDatabaseConfig().getLocalConnection();
		try ( Transaction tx = database.beginTx() )
		{
		    // Database operations go here
			/*Node javaNode = database.createNode();
			javaNode.setProperty("TutorialID", "JAVA001");
			javaNode.setProperty("Title", "Learn Java");
			javaNode.setProperty("NoOfChapters", "25");
			javaNode.setProperty("Status", "Completed");				
			
			Node scalaNode = database.createNode();
			scalaNode.setProperty("TutorialID", "SCALA001");
			scalaNode.setProperty("Title", "Learn Scala");
			scalaNode.setProperty("NoOfChapters", "20");
			scalaNode.setProperty("Status", "Completed");
			
			Relationship relationship = javaNode.createRelationshipTo
			(scalaNode,RelationshipTypes.KNOWS);
			relationship.setProperty("Id","1234");
			relationship.setProperty("OOPS","YES");
			relationship.setProperty("FP","YES");*/
			
			for (Node node : database.getAllNodes()) {
				System.out.println(node.getProperty("TutorialID"));
				System.out.println(node);
			}
			
		    tx.success();
		}
		
	}

	@Override
	public SessionFactory getSessionFactory() {
		return new SessionFactory(getConfiguration(), "com.prapps.tutorial.spring.neo4j.persistence");
	}
	
	@Bean
	public org.neo4j.ogm.config.Configuration getConfiguration() {
	   org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
	   config
	       .driverConfiguration()
	       .setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver");
	   return config;
	}
}