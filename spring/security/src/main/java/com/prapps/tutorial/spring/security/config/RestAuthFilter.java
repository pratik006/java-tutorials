package com.prapps.tutorial.spring.security.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class RestAuthFilter extends AbstractAuthenticationProcessingFilter {

	private ObjectMapper mapper;

	@Autowired
	public RestAuthFilter(RestAuthenticationManager restAuthenticationManager,
			@Qualifier("restAuthenticationSuccessHandler") RestAuthenticationSuccessHandler restAuthenticationSuccessHandler,
			ObjectMapper mapper) {
		super("/rest/login");
		this.setAuthenticationManager(restAuthenticationManager);
		this.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler);
		this.mapper = mapper;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
          BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
          while ((line = reader.readLine()) != null)
            jb.append(line);
        } catch (Exception e) { /*report an error*/ }

        String username = null;
        String password = null;
        try {
			JsonNode rootNode = mapper.readTree(jb.toString());
			username = rootNode.get("username").textValue();
			password = rootNode.get("password").textValue();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


        if(username == null || password == null) {
        	throw new AuthenticationServiceException("Username or Password cannot be empty ");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password, null);
        Authentication authResult = getAuthenticationManager().authenticate(authentication);
        if (!authResult.isAuthenticated()) {
        	throw new AuthenticationServiceException("Authentication failed");
        }
        return new UsernamePasswordAuthenticationToken(username, null, authResult.getAuthorities());
    }

}
