package com.hcl.smart.bank.operations.client;

import java.io.IOException;

import javax.naming.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * 
 * @author shaveta.m
 *
 */
public class RestTemplateResponseHandler implements ResponseErrorHandler{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestCallService.class);

	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		if (httpResponse.getStatusCode() == HttpStatus.FORBIDDEN) {
			LOGGER.debug(HttpStatus.FORBIDDEN + " response. Throwing authentication exception");
			try {
				throw new AuthenticationException();
			} catch (AuthenticationException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		if (httpResponse.getStatusCode() != HttpStatus.OK) {
			LOGGER.debug("Status Code : "+httpResponse.getStatusCode());
			LOGGER.debug("Response :" + httpResponse.getStatusText());
			LOGGER.debug("Response Body: "+ httpResponse.getBody());
			if (httpResponse.getStatusCode() == HttpStatus.FORBIDDEN) {
				LOGGER.debug("Call returned a error 403 forbidden resposne ");
				return true;
			}
		}
		return false;
	}

}
