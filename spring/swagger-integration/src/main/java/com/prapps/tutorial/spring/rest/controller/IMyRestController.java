package com.prapps.tutorial.spring.rest.controller;

import com.prapps.tutorial.spring.dto.HelloResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/rest")
@Api(value = "allcontrols", description = "just a trail response")
public interface IMyRestController {
    @ApiOperation(value="hello", response = HelloResponse.class)
    @RequestMapping(value="/hello", method = {RequestMethod.GET, RequestMethod.POST},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    HelloResponse hello();

    @ApiOperation(value="testerror", response = HelloResponse.class)
    @RequestMapping(value="/testerror", method = {RequestMethod.GET, RequestMethod.POST},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    HelloResponse testerror() throws IOException;

    @RequestMapping(value="/manage", method = {RequestMethod.GET, RequestMethod.POST})
    HelloResponse manage();
}
