package com.prapps.example;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive/person")
class PersonController {

    private PersonService personService;

    @Autowired
    PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Person> getAll() {
        return personService.getAll();
    }

    @PostMapping
    public Mono<Person> save(@RequestBody Person person) {
        return personService.save(person);
    }

    @GetMapping("/{id}")
    public Mono<Person> findOne(@PathVariable Long id) {
        return personService.getOne(id);
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<PersonEvent> getEvents(@PathVariable Long id) {
        return personService.events(id);
    }

    @GetMapping(value = "/dummy", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<String> dummy() {
        WebClient client = WebClient.builder().baseUrl("https://reqres.in/api/users").build();
        Mono<ObjectNode> f1 = client.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ObjectNode.class);
        Mono<String> f2 = f1.flatMap(users -> {
            int userId = users.withArray("data").get(0).get("id").asInt();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return client.get().uri("/" + userId).retrieve().bodyToMono(String.class);
        });

        //Flux<String> result = f1.map(i -> "response 1: "+i.toString());
        //Flux<String> r3 = f2.map(r2->"response 2: "+r2);
        //result.concatWith(r3);

        return f1.map(r -> r.toString()+"\r\n\r\n\r\n").concatWith(f2);
    }
}
