package com.prapps.tutorial.spring.netflix.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClientApplication implements GreetingController {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

    public String greeting() {
        System.out.println("hash: "+this.hashCode());
        return "Hello from EurekaClient!";
    }
}
