package com.prapps.example;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

//@SpringBootTest("application.properties")
public class WebClientTest {

    @Test
    public void testMono() throws InterruptedException {
        WebClient client = WebClient.builder().baseUrl("https://reqres.in/api/users").build();
        Mono<String> resp = client.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ObjectNode.class)
                .flatMap(users -> {
                    int userId = users.withArray("data").get(0).get("id").asInt();
                    Mono<String> resp2 = client.get().uri("/" + userId).retrieve().bodyToMono(String.class);
                    return resp2;
                });

        System.err.println(resp.block());
    }

    @Test
    public void testFlux() throws InterruptedException {
        final AtomicInteger ctr = new AtomicInteger();
        WebClient client = WebClient.builder().baseUrl("http://localhost:8080/reactive/person")
                .filter(ExchangeFilterFunctions.basicAuthentication("user", "password")).build();
        client.get().uri("/dummy")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(String.class)
                .subscribe(r -> {
                    System.err.println(r);
                    if (r.startsWith("{\"data\"")) {
                        synchronized (ctr) {
                            ctr.notify();
                        }
                    }
                });

        synchronized (ctr) {
            ctr.wait(10000);
        }
    }
}
