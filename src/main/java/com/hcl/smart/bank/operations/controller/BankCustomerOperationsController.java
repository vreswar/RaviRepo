package com.hcl.smart.bank.operations.controller;
/**
 * 
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.smart.bank.operations.exception.MessageCodeEnum;
import com.hcl.smart.bank.operations.exception.SmartBankCustomerException;
import com.hcl.smart.bank.operations.service.ICustomerService;
import com.hcl.swagger.smart.bank.customer.model.BankCustomerFindByAccountNumberRequest;
import com.hcl.swagger.smart.bank.customer.model.BankCustomerRequest;
import com.hcl.swagger.smart.bank.customer.model.BankCustomerResponse;

/**
 * @author pushpeswar.r
 *
 */
@RestController
@RequestMapping(value = "${app.context.path}")
public class BankCustomerOperationsController {
	Logger LOGGER = LoggerFactory.getLogger(BankCustomerOperationsController.class);
	
	@Autowired
	private ICustomerService customerService;
	
	@CrossOrigin
	@RequestMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public BankCustomerResponse verifyLogin(@RequestBody BankCustomerRequest request)
			throws SmartBankCustomerException {
		LOGGER.info("In verifyLogin meethod, to add customer");
		if(request != null) {
			BankCustomerResponse response = customerService.addCustomer(request);
			return response;
		} else {
			throw new SmartBankCustomerException(MessageCodeEnum.CREATE_TRANSACTION_FAILURE, "Incomplete or no request data provided to create customer");
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = "/findCustomerByAccountNumber", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public BankCustomerResponse findCustomerByAccountNumber(
			@RequestBody BankCustomerFindByAccountNumberRequest request) throws SmartBankCustomerException {
		LOGGER.info("In findCustomerByAccountNumber method to find customer details");
		if (request != null && !request.getAccountNumber().isEmpty()) {
			BankCustomerResponse response = customerService
					.findCustomerByAccountNumber(Integer.valueOf(request.getAccountNumber()));
			return response;
		} else {
			throw new SmartBankCustomerException(MessageCodeEnum.CUSTOMER_LOOKUP_FAILED, "Provide account number to fetch details");
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = "/updateCustomer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public BankCustomerResponse updateCustomer(@RequestBody BankCustomerRequest request)
			throws SmartBankCustomerException {
		LOGGER.info("In updateCustomer method to update customer details");
		if (request != null) {
			BankCustomerResponse response = customerService.updateCustomer(request);
			return response;
		} else {
			throw new SmartBankCustomerException(MessageCodeEnum.UPDATE_TRANSACTION_FAILURE, "Some issue occured while updating customer information. Please try later.");
		}
	}
}