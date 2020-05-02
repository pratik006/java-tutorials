package com.prapps.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableReactiveMongoRepositories
public class ReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}

	@Bean
	public CommandLineRunner setUpRecords(@Autowired final PersonRepo personRepo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Flux<Person> persons = Flux.just(new Person(1L, "Roy", "Keane"),
						new Person(2L, "David", "Bekham"),
						new Person(3L, "Nikky", "Butt"));
				persons.flatMap(personRepo::save).subscribe(System.out::println);
			}
		};
	}
}
