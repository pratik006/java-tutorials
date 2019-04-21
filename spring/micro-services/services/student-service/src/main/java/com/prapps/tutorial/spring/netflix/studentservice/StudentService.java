package com.prapps.tutorial.spring.netflix.studentservice;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping
public class StudentService {

    private StudentConsumerService studentConsumerService;
    @Autowired
    private EurekaClient discoveryClient;
    RestTemplate restTemplate;

    @Autowired
    public StudentService(StudentConsumerService studentConsumerService) {
        this.studentConsumerService = studentConsumerService;
        restTemplate = new RestTemplate();
    }

    @GetMapping("/search")
    public List<Student> findStudentsByName(@RequestParam ("name") String name) {
        System.out.println("hashCode of studentConsumerService: "+studentConsumerService.hashCode());
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("db-service", true);
        String targetUrl = instance.getHomePageUrl();
        ResponseEntity<List<Student>> rateResponse =
                restTemplate.exchange(targetUrl + "/rest/db/student/"+name,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
                        });
        List<Student> students = rateResponse.getBody();

        return students;
        //return studentConsumerService.findStudentsByName(name);
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
