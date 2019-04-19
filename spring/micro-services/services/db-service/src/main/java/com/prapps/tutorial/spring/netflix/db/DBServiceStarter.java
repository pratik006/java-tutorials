package com.prapps.tutorial.spring.netflix.db;

import com.prapps.tutorial.spring.netflix.db.entity.Course;
import com.prapps.tutorial.spring.netflix.db.entity.Student;
import com.prapps.tutorial.spring.netflix.db.repo.CourseRepo;
import com.prapps.tutorial.spring.netflix.db.repo.StudentRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.prapps.tutorial.spring.netflix.db")
@EnableEurekaClient
public class DBServiceStarter {
    public static void main(String[] args) {
        SpringApplication.run(DBServiceStarter.class, args);
    }

    //for setting up initial data
    /*@EventListener(ApplicationReadyEvent.class)
    public void initData(final ApplicationReadyEvent event) {
        event.getApplicationContext().getBean(StudentRepo.class).saveAll(Stream.<Student>of(
                new Student("Pratik", "Sengupta"),
                new Student("Pratik", "Das"))
                .collect(Collectors.toSet()));

        event.getApplicationContext().getBean(CourseRepo.class).saveAll(Stream.<Course>of(
                new Course("Diploma in Software Engg", "6 month"),
                new Course("Diploma in Mechanical Engg", "6 month"),
                new Course("Summer Internship in Java", "3 month"))
                .collect(Collectors.toList()));
    }*/
}