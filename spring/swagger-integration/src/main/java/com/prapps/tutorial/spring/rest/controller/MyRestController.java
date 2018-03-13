package com.prapps.tutorial.spring.rest.controller;

import com.prapps.tutorial.spring.dto.HelloResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyRestController implements IMyRestController {
	public HelloResponse hello() {
		return new HelloResponse("hello");
	}
    public HelloResponse testerror() throws IOException {
        throw new IOException("testing");
    }
	public HelloResponse manage() {
		return new HelloResponse("manage");
	}
}
