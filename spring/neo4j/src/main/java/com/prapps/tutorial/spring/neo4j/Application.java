package com.prapps.tutorial.spring.neo4j;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.neo4j.NodeEntityScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
//@NodeEntityScan(basePackages = "com.prapps.tutorial.spring.neo4j.persistence")
//@EntityScan(basePackageClasses=Train.class)
public class Application /*extends Neo4jConfiguration*/ {

	public static void main(String[] args) throws IOException {
		//FileUtils.deleteRecursively(new File("target/neo.db"));
		SpringApplication.run(Application.class, args);
	}

}