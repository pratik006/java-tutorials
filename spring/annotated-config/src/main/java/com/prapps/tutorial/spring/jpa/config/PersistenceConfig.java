package com.prapps.tutorial.spring.jpa.config;

import java.util.Properties;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Singleton
public class PersistenceConfig {

	private static final String PERSISTENT_UNIT_NAME = "employee-persister";
	
	@Bean
	@Scope("singleton")
	public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
		System.err.println(entityManagerFactory.createEntityManager());
		return entityManagerFactory.createEntityManager();
	}
	
	@Bean(name=PERSISTENT_UNIT_NAME)
	@Scope("singleton")
	@DependsOn("dataSource")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		Properties entityManagerProperties = new Properties();
		entityManagerProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		entityManagerProperties.put("hibernate.hbm2ddl.auto", "create-drop");
		entityManagerProperties.put("show_sql", "true");
		entityManagerProperties.put("hibernate.show_sql", "true");
		entityManagerProperties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
		
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.prapps.tutorial.spring.entities" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(entityManagerProperties);
		return em;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=true;");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
