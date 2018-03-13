package com.prapps.tutorial.spring.rest.controller;

import com.prapps.tutorial.spring.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class MyExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
    public void error(HttpServletResponse response, Exception ex) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMsg("Generic Error");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IOException.class)
    public @ResponseBody ErrorResponse error2(HttpServletResponse response, Exception ex) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMsg("bad request header");
        errorResponse.setThrowable(ex.getCause());
        return errorResponse;
    }
}
