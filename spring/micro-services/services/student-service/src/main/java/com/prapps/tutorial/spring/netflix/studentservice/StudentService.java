package com.prapps.tutorial.spring.netflix.studentservice;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private RestTemplate restTemplate;
    @Value("${services.core-api.host}")
    private String dbServiceUrl;

    @Autowired
    public StudentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/search")
    public StudentSearchResponse findStudentsByName(@RequestParam ("name") String name) {
        String targetUrl = dbServiceUrl + "/rest/db/student/"+name;
        System.out.println("targetUrl: "+targetUrl);
        List<Student> students = restTemplate.exchange(targetUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Student>>() { }).getBody();
        System.out.println("got response");
        StudentSearchResponse response = new StudentSearchResponse(students);
        response.getMessages().add("hashCode of StudentService: "+hashCode() );
        return response;
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
