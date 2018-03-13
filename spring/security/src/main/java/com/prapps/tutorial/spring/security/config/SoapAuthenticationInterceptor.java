package com.prapps.tutorial.spring.security.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.SoapBody;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.w3c.dom.Node;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import java.util.Locale;

@Component
public class SoapAuthenticationInterceptor implements EndpointInterceptor {
	public static final String MISSING_JWT_TOKEN = "No JWT token found in request headers";

	@Override
	public boolean handleRequest(MessageContext ctx, Object arg1) throws Exception {
		SoapMessage soapMessage  = (SoapMessage) ctx.getRequest();
		SoapHeader soapHeader = soapMessage.getSoapHeader();
		Source bodySource = soapHeader .getSource();
		DOMSource bodyDomSource = (DOMSource) bodySource;
		Node bodyNode = bodyDomSource.getNode();
		if (bodyNode.getFirstChild() == null) {
			SoapBody soapBody = soapMessage.getSoapBody();
			soapBody.addClientOrSenderFault(MISSING_JWT_TOKEN, Locale.ENGLISH);
			throw new SoapFaultClientException(soapMessage);
		}

		String token = bodyNode.getTextContent();
		if (token == null) {
			SoapBody soapBody = soapMessage.getSoapBody();
			soapBody.addClientOrSenderFault(MISSING_JWT_TOKEN, Locale.ENGLISH);
			throw new SoapFaultClientException(soapMessage);
		}
		UserDetails user = JwtTokenHelper.verifyToken(token);
		return user != null;
	}

	@Override public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception { return false; }

	@Override public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception { return false; }

	@Override public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception { }

}
