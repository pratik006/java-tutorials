package com.prapps.tutorial.spring.netflix.studentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class StudentService {

    //@LoadBalanced
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
}
