package com.prapps.tutorial.spring.netflix.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface StudentService {
    @RequestMapping("/greeting")
    String greeting();

    @GetMapping("db-service/rest/db/student/pratik")
    List<Student> findStudents(String name);
}
