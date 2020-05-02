package com.prapps.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReactiveApplicationTests {
	@Autowired
	WebTestClient webTestClient;

	@Test
	void testCreatePerson() {
		String fName = "Pratik";
		String lName = "Sengupta";
		webTestClient
				.post().uri("/reactive/person").contentType(MediaType.APPLICATION_JSON)
				.body(Mono.just(new Person(101L, "Pratik", "Sengupta")), Person.class)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Person.class)
				.consumeWith(resp -> {
					Person savedPerson = resp.getResponseBody();
					assertEquals(fName, savedPerson.getFirstName());
					assertEquals(lName, savedPerson.getLastName());
					assertNotNull(savedPerson.getId());
				});
	}

	@Test
	void testGetAllPersons() {
		webTestClient.get().uri("/reactive/person").accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk();
	}

}
