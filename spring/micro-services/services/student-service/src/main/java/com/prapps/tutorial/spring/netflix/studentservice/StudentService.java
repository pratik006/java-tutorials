package com.prapps.tutorial.spring.netflix.studentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping
public class StudentService {

    private StudentConsumerService studentConsumerService;

    @Autowired
    public StudentService(StudentConsumerService studentConsumerService) {
        this.studentConsumerService = studentConsumerService;
    }

    @GetMapping("/search")
    public List<Student> findStudentsByName(@RequestParam ("name") String name) {
        System.out.println("hashCode of studentConsumerService: "+studentConsumerService.hashCode());
        return studentConsumerService.findStudentsByName(name);
    }


    @GetMapping("/tryluck")
    //@HystrixCommand(fallbackMethod = "tryLuckFallback", commandKey = "failluck", groupKey = "failluck")
    public String tryLuck() {
        if (new Random().nextBoolean()) {
            throw new RuntimeException();
        }

        return "success";
    }

    public String tryLuckFallback() {
        return "fallback for tryluck initiated";
    }

    @GetMapping("/tryhash")
    public String tryHash() {
        throw new StudentServiceException();
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "student resource not found")
    public class StudentServiceException extends RuntimeException {
    }
}
