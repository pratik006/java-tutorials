package com.prapps.tutorial.spring.netflix.studentservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("db-service")
public interface StudentConsumerService {

    @GetMapping(value = "rest/db/student/{name}", consumes = "application/json")
    List<Student> findStudentsByName(@PathVariable("name") String name);
}
