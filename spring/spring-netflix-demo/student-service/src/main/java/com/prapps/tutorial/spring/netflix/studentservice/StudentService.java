package com.prapps.tutorial.spring.netflix.studentservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @HystrixCommand(fallbackMethod = "tryLuckFallback", commandKey = "failluck", groupKey = "failluck")
    public String tryLuck() {
        if (new Random().nextBoolean()) {
            throw new RuntimeException();
        }

        return "success";
    }

    public String tryLuckFallback() {
        return "fallback for tryluck initiated";
    }
}
