package com.prapps.tutorial.spring.rest.controller;

import com.prapps.tutorial.spring.dto.HelloResponse;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/secured")
public interface IMyRestController {
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value="/hello", method = {RequestMethod.GET, RequestMethod.POST})
    HelloResponse hello();

    @RequestMapping(value="/manage", method = {RequestMethod.GET, RequestMethod.POST})
    @Secured("ROLE_ADMIN")
    HelloResponse manage();
}
