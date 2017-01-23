package com.prapps.spring.neo4j.demo;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"com.prapps"})
@EnableNeo4jRepositories("com.prapps.spring.neo4j.demo.persistence.repo")
@EntityScan(basePackages = "com.prapps.spring.neo4j.demo.persistence")
@EnableTransactionManagement
public class Neo4DemoApplication extends Neo4jConfiguration {

	public static void main(String[] args) {
		SpringApplication.run(Neo4DemoApplication.class, args);
	}
	
	@Bean
	@Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory("com.prapps.spring.neo4j.demo.persistence");
    }
	
	@Bean
	@Override
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Session getSession() throws Exception {
		return super.getSession();
	}
}
