package com.prapps.tutorial.spring.netflix.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.prapps.tutorial.spring.netflix.db")
@Configuration
@PropertySource("classpath:application.yaml")
public class DBServiceStarter {

    public static void main(String[] args) {
        System.out.println("ENV variables -");
        System.getenv().entrySet().stream()
                //.filter(entry -> entry.getKey().contains("DB"))
                .forEach(entry -> System.out.println(entry.getKey()+": "+entry.getValue()));

        System.getProperties().entrySet().stream()
                //.filter(entry -> entry.getKey().toString().contains("MYSQL"))
                .forEach(entry-> System.out.println(entry.getKey()+": "+entry.getValue()));
        SpringApplication.run(DBServiceStarter.class, args);
    }

    //for setting up initial data
    /*@EventListener(ApplicationReadyEvent.class)
    public void initData(final ApplicationReadyEvent event) {
        event.getApplicationContext().getBean(StudentRepo.class).saveAll(Stream.<Student>of(
                new Student("Pratik", "Sengupta"),
                new Student("Pratik", "Das"))
                .collect(Collectors.toSet()));
    }*/
}