package com.prapps.tutorial.spring.neo4j;

import java.io.File;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseBuilder;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.prapps.tutorial.spring.neo4j.repo")
public class ApplicationConfig extends Neo4jConfiguration {

	@Bean
    public SessionFactory getSessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory(getConfiguration(), "com.prapps.tutorial.spring.neo4j.persistence");
    }

    // needed for session in view in web-applications
    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Session getSession() throws Exception {
        return super.getSession();
    }
    
    @Bean
    protected GraphDatabaseService createDb() {
    	return new GraphDatabaseFactory()
			.newEmbeddedDatabase(new File ("target/graphdb"));
    }
    
    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
       org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
       config
           .driverConfiguration()
           /*.setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
           .setURI("http://neo4j:password@localhost:7475");*/
           
           .setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver");
       return config;
    }

}
