package com.hcl.smart.bank.operations.client;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.smart.bank.operations.exception.MessageCodeEnum;
import com.hcl.smart.bank.operations.exception.SmartBankCustomerException;
import com.hcl.swagger.smart.bank.customer.model.Errors;

@Component
public class RestCallService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestCallService.class);

	RestTemplate restTemplate = new RestTemplate();
	
	static {
	    //for localhost testing only
	    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
	    new javax.net.ssl.HostnameVerifier(){

	        public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
	                return true;
	        }
	    });
	}	
	
	@SuppressWarnings("unchecked")
	public <T> T invokeRestTemplate(String url, Object bankCustomerRequest, Class<T> responseType) throws SmartBankCustomerException {
		LOGGER.info("inside invokeRestTemplate start");
		String resultStr = null;
		ObjectMapper mapper = new ObjectMapper();			
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.setSerializationInclusion(Include.NON_EMPTY);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String jsonInString = null;
		
		try {
			jsonInString = mapper.writeValueAsString(bankCustomerRequest);
			LOGGER.info("inputStr :"+jsonInString);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<String> entity = new HttpEntity<String>(jsonInString,headers);	
			restTemplate.setErrorHandler(new RestTemplateResponseHandler());		
			ResponseEntity<String> result = restTemplate.postForEntity(url, entity,String.class);
			LOGGER.info("result status code :"+result.getStatusCode());
			LOGGER.info("result body :"+result.getBody());
			resultStr = result.getBody();
			if (result.getStatusCode() == HttpStatus.OK) {
				bankCustomerRequest = mapper.readValue(resultStr, responseType);
			} else {
				TypeReference<HashMap<String,Errors>> typeRef = new TypeReference<HashMap<String,Errors>>() {};
				Map<String, Errors> responseError = mapper.readValue(resultStr, typeRef);
				LOGGER.error("ResponseError:"+responseError.toString());
				throw new SmartBankCustomerException(responseError.get("errors"), result.getStatusCode());	
			}			
			
			LOGGER.info("inside invokeRestTemplate End");
		} catch (SmartBankCustomerException exception) {
			throw exception;
		} catch(Exception exception) {
			throw new SmartBankCustomerException(MessageCodeEnum.FAILED_REQUEST, exception.toString());
		}
		return (T) bankCustomerRequest;
	}

	/**
	 * Added for gateway manger changes
	 * 
	 * @param url
	 * @param requestBody
	 * @param httpMethod
	 * @return
	 * @throws SmartBankCustomerException 
	 */
	public String invokeRestfulExchange(String url, Object requestBody, HttpMethod httpMethod) throws SmartBankCustomerException {
		LOGGER.info("Invoke restfulExchange :: [{}], [{}], [{}] ", url, requestBody, httpMethod);
		try {
			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody,headers);
			RestTemplate restTemplate=new RestTemplate();
			ResponseEntity<?> response = restTemplate.exchange(url, httpMethod, requestEntity, String.class);
			LOGGER.info("Response :: [{}] ", url, response.getBody().toString());
			
			return response.getBody().toString();
		} catch(Exception exception) {
			throw new SmartBankCustomerException(MessageCodeEnum.FAILED_REQUEST, exception.toString());
		}
	}
}
