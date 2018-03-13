package com.prapps.tutorial.spring.rest.controller;

import com.prapps.tutorial.spring.dto.HelloResponse;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Component
public class MyRestController implements IMyRestController {
	public HelloResponse hello() {
		return new HelloResponse("hello");
	}
	public HelloResponse manage() {
		return new HelloResponse("manage");
	}
}
