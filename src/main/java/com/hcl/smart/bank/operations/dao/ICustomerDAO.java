package com.hcl.smart.bank.operations.dao;

import com.hcl.smart.bank.operations.entity.Customer;
import com.hcl.smart.bank.operations.exception.SmartBankCustomerException;

public interface ICustomerDAO {
	void addCustomer(Customer customer);
	Customer findCustomerByAccountNumber(Integer pCustomerAccountNumber) throws SmartBankCustomerException;
	Customer updateCustomer(Customer pCustomer) throws SmartBankCustomerException;
}