package com.hcl.smart.bank.operations.service;

import com.hcl.smart.bank.operations.exception.SmartBankCustomerException;
import com.hcl.swagger.smart.bank.customer.model.BankCustomerRequest;
import com.hcl.swagger.smart.bank.customer.model.BankCustomerResponse;

public interface ICustomerService {
	BankCustomerResponse addCustomer(BankCustomerRequest request) throws SmartBankCustomerException;
	BankCustomerResponse findCustomerByAccountNumber(Integer pCustomerAccount) throws SmartBankCustomerException;
	BankCustomerResponse updateCustomer(BankCustomerRequest request) throws SmartBankCustomerException;
}