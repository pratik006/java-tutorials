package com.prapps.tutorial.ejb.rest.interceptor;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import com.prapps.tutorial.ejb.rest.ResponseStatusType;
import com.prapps.tutorial.ejb.rest.model.BaseResponse;

@Provider
public class ResponseHelperInterceptor implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext req, ContainerResponseContext res) throws IOException {
		if (res.getEntity() instanceof BaseResponse) {
			BaseResponse response = (BaseResponse) res.getEntity();
			if (response.getStatus() == null || response.getStatus().isEmpty()) {
				response.setStatus(response.getErrors().isEmpty() ? 
					ResponseStatusType.SUCCESS : ResponseStatusType.FAILURE);
			}
		}
	}

}
