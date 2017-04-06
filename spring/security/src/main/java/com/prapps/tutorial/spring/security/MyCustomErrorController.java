package com.prapps.tutorial.spring.security;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyCustomErrorController implements ErrorController {

	@RequestMapping(value="/error")
    public String error() {
        return "Error heaven";
    }
	
	@Override
	public String getErrorPath() {
		return "error";
	}

}
