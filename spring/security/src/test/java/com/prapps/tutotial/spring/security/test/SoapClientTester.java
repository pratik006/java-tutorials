package com.prapps.tutotial.spring.security.test;

import com.prapps.tutorial.spring.ApplicationStarter;
import com.prapps.tutorial.spring.GetCountryRequest;
import com.prapps.tutorial.spring.GetCountryResponse;
import com.prapps.tutotial.spring.security.test.config.SoapClientConfig;
import com.prapps.tutotial.spring.security.test.config.SoapClientHeaderInterceptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.client.SoapFaultClientException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, properties = {"server.port: 8080"})
@AutoConfigureMockMvc
@ContextConfiguration(classes = {ApplicationStarter.class, SoapClientConfig.class})
public class SoapClientTester {

	private String username;
	private String password;
	private SoapClientHeaderInterceptor soapClientHeaderInterceptor;
	@Value("${server.port}")
	private String port;

	@Autowired MockMvc mvc;
	@Autowired WebApplicationContext wac;
	@Autowired WebServiceTemplate webServiceTemplate;

	@Before
	public void setUp() {
		username = "admin";
		password = "admin";
		soapClientHeaderInterceptor = new SoapClientHeaderInterceptor(username, password);
	}

	@Test
	public void shouldAccessSecuredSoapResource() throws Exception {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		webServiceTemplate.setDefaultUri("/ws");
		webServiceTemplate.setInterceptors(new ClientInterceptor[] {soapClientHeaderInterceptor});
		GetCountryRequest request = new GetCountryRequest();
		request.setName("Spain");
		GetCountryResponse actualResp = (GetCountryResponse) webServiceTemplate
	                .marshalSendAndReceive("http://localhost:"+port+"/security-demo/ws", request);
		Assert.assertEquals("Spain", actualResp.getCountry().getName());
	}

	@Test(expected = SoapFaultClientException.class)
	public void shouldFailAccesSecuredSoapResource() throws Exception {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		webServiceTemplate.setDefaultUri("/ws");
		GetCountryRequest request = new GetCountryRequest();
		request.setName("Spain");
		webServiceTemplate
	                .marshalSendAndReceive("http://localhost:"+port+"/security-demo/ws", request);
	}
}
