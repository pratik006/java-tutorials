package com.prapps.tutorial.spring.netflix.client.consumer;

import com.prapps.tutorial.spring.netflix.client.GreetingController;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("spring-cloud-eureka-client")
public interface GreetingClient extends GreetingController {
}
