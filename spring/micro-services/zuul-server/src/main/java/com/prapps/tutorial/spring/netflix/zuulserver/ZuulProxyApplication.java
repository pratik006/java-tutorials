package com.prapps.tutorial.spring.netflix.zuulserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
public class ZuulProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulProxyApplication.class, args);
	}

}
