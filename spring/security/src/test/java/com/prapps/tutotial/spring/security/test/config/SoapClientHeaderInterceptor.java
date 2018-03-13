package com.prapps.tutotial.spring.security.test.config;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.xml.transform.StringSource;

import com.prapps.tutorial.spring.security.config.JwtTokenHelper;
import com.prapps.tutorial.spring.soap.Authentication;

public class SoapClientHeaderInterceptor implements ClientInterceptor {

	private String username;
	private String password;

	public SoapClientHeaderInterceptor(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
		return false;
	}

	@Override
	public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
		SoapMessage soapMessage = (SoapMessage) messageContext.getRequest();
		SoapHeader soapHeader = soapMessage.getSoapHeader();
		StringSource headerSource = new StringSource(prepareSecurityHeader());
		Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(headerSource, soapHeader.getResult());
		} catch (TransformerException | TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}

		return true;
	}

	private String prepareSecurityHeader() {
		String result = "";
		StringWriter sw = new StringWriter();
		try {
			String token = JwtTokenHelper.createJsonWebToken(
					new UsernamePasswordAuthenticationToken(username, password, null));
			Authentication auth = new Authentication();
			auth.setToken(token);
			JAXBContext context = JAXBContext.newInstance(Authentication.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshaller.marshal(auth, sw);
			result = sw.toString();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
		return false;
	}

	@Override
	public void afterCompletion(MessageContext messageContext, Exception ex) throws WebServiceClientException {
	}

}
