package com.prapps.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PersonEvent> getEvents(@PathVariable Long id) {
        return personService.events(id);
    }
}
