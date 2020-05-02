package com.prapps.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReactiveApplicationTests {

	private static final String REACTIVE_EP = "/reactive/person";
	private WebTestClient webTestClient;

	@Autowired
	ReactiveApplicationTests(WebTestClient webTestClient) {
		this.webTestClient = webTestClient;
	}

	@Test
	void testCreateAndGetPerson() {
		String fName = "Pratik";
		String lName = "Sengupta";
		webTestClient
				.post().uri(REACTIVE_EP).contentType(MediaType.APPLICATION_JSON)
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
					webTestClient.get().uri(REACTIVE_EP+"/"+savedPerson.getId()).exchange().expectStatus().isOk().expectBody(Person.class).consumeWith(getResp -> {
						Person retrievedPerson = getResp.getResponseBody();
						assertEquals(savedPerson, retrievedPerson);
					});
				});
	}

	@Test
	void testGetAllPersons() {
		webTestClient.get().uri(REACTIVE_EP).accept(MediaType.APPLICATION_STREAM_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(Person.class)
				.consumeWith(r -> {
					assertEquals(new HashSet<>(Arrays.asList("Roy", "David", "Nikky")), r.getResponseBody().stream()
													.map(p -> p.getFirstName())
													.collect(Collectors.toSet()));
				});
	}

}
