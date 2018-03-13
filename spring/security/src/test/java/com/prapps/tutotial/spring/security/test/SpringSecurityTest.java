package com.prapps.tutotial.spring.security.test;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prapps.tutorial.spring.ApplicationStarter;
import com.prapps.tutorial.spring.dto.HelloResponse;
import com.prapps.tutorial.spring.security.config.JwtTokenHelper;
import com.prapps.tutorial.spring.security.config.RestAuthFilter;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {ApplicationStarter.class})
public class SpringSecurityTest {
	private static final Logger LOG = Logger.getLogger(SpringSecurityTest.class);

	private String username;
	private String password;
	private GrantedAuthority auth;

	@Autowired MockMvc mvc;
	@Autowired WebApplicationContext wac;
	@Autowired RestAuthFilter restAuthFilter;

	@Before
	public void setUp() {
		username = "admin";
		password = "admin";
		auth = new GrantedAuthority() {
			private static final long serialVersionUID = 1L;
			@Override
			public String getAuthority() { return "ROLE_ADMIN"; }
		};
		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void shouldGetToken() throws Exception {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.apply(springSecurity())
				.addFilters(restAuthFilter)
				.build();
		mvc.perform(MockMvcRequestBuilders.post("/rest/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\": \""+username+"\", \"password\": \""+password+"\"}")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(new ResultHandler() {
				@Override
				public void handle(MvcResult result) throws Exception {
					String token = result.getResponse().getHeader("x-authtoken");
					String contentType = result.getResponse().getHeader("Content-Type");
					String content = new String(result.getResponse().getContentAsByteArray());

					LOG.debug("contentType: "+contentType+"\tcontent: " + content);
					LOG.debug("token: " + token);
					UserDetails userDetials = JwtTokenHelper.verifyToken(token);
					LOG.debug("username: " + userDetials.getUsername());
  					Assert.assertEquals(username, userDetials.getUsername());
  					Assert.assertEquals(auth.getAuthority(), userDetials.getAuthorities().iterator().next().getAuthority());
				}
			});
	}

	@Test(expected = Exception.class)
	public void shouldFailWithUnauthException() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/rest/secured/hello"));
	}

	@Test @WithMockUser(username = "user", password = "user", roles = "USER")
	public void shouldAccessSecuredResource() throws Exception {
		MvcResult mvcResult =  mvc.perform(post("/rest/secured/hello")
			.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andReturn();
		ObjectMapper mapper = new ObjectMapper();
		String jsonResponse = mvcResult.getResponse().getContentAsString();
		LOG.debug("Response: " + jsonResponse);
		HelloResponse actualResp = mapper.readValue(jsonResponse, HelloResponse.class);
		Assert.assertEquals("hello", actualResp.getMessage());
	}

	@Test @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void shouldAccessSecuredAdminResource() throws Exception {
		MvcResult mvcResult =  mvc.perform(post("/rest/secured/manage")
			.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andReturn();
		ObjectMapper mapper = new ObjectMapper();
		String jsonResponse = mvcResult.getResponse().getContentAsString();
		LOG.debug("Response: " + jsonResponse);
		HelloResponse actualResp = mapper.readValue(jsonResponse, HelloResponse.class);
		Assert.assertEquals("manage", actualResp.getMessage());
	}

	@Test @WithMockUser(username = "user", password = "user", roles = "USER")
	public void shouldFailSecuredAdminResource() throws Exception {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.apply(springSecurity())
				.addFilters(restAuthFilter)
				.build();
		mvc.perform(post("/rest/secured/manage").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
	}
}
