package com.prapps.tutorial.ejb.rest.exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.prapps.tutorial.ejb.rest.ResponseStatusType;
import com.prapps.tutorial.ejb.rest.exception.type.ErrorCodeType;
import com.prapps.tutorial.ejb.rest.exception.type.ServiceErrorMessage;
import com.prapps.tutorial.ejb.rest.model.BaseResponse;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

	private static final Logger LOG = Logger.getLogger(ServiceExceptionMapper.class.getName());
	
	@Override
	public Response toResponse(ServiceException e) {
		BaseResponse response = new BaseResponse();
		for (ErrorCodeType errorCodeType : e.getErrorCodeTypes()) {
			ServiceErrorMessage serviceErrorMessage = new ServiceErrorMessage();
			serviceErrorMessage.setErrorCode(errorCodeType.getErrorCode());
			serviceErrorMessage.setErrorMsg(properties.get(String.valueOf(errorCodeType.getErrorCode())));
			response.getErrors().add(serviceErrorMessage);
		}
		response.setStatus(ResponseStatusType.FAILURE);
		return Response.ok(response).build();
	}
	
	private Map<String, String> properties = new HashMap<>();
    
    @PostConstruct
    private void init() {
        //matches the property name as defined in the system-properties element in WildFly
    	InputStream is = this.getClass().getClassLoader().getResourceAsStream("error-message.properties");
        Properties properties = new Properties();
          
        try {
            properties.load(is);
        } catch (IOException e) {
        	LOG.log(Level.SEVERE, "Unable to load properties file" + e);
        }
          
        Map hashMap = new HashMap<>(properties);
        this.properties.putAll(hashMap);
    }
  
    public String getProperty(String key) {
        return properties.get(key);
    }

}
