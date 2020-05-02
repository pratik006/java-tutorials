package com.prapps.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
class PersonService {
    private PersonRepo personRepo;

    @Autowired
    PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public Mono<Person> save(Person person) {
        return personRepo.save(person);
    }

    public Flux<Person> getAll() {
        return personRepo.findAll();
    }

    public Mono<Person> getOne(Long id) {
        return personRepo.findById(id);
    }
}
