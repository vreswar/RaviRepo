package com.hcl.smart.bank.operations.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.smart.bank.operations.dao.ICustomerDAO;
import com.hcl.smart.bank.operations.entity.Customer;
import com.hcl.smart.bank.operations.entity.CustomerAccount;
import com.hcl.smart.bank.operations.entity.CustomerAddress;
import com.hcl.smart.bank.operations.exception.MessageCodeEnum;
import com.hcl.smart.bank.operations.exception.SmartBankCustomerException;
import com.hcl.swagger.smart.bank.customer.model.BankCustomerAccount;
import com.hcl.swagger.smart.bank.customer.model.BankCustomerAddress;
import com.hcl.swagger.smart.bank.customer.model.BankCustomerRequest;
import com.hcl.swagger.smart.bank.customer.model.BankCustomerResponse;

@Service
public class CustomerService implements ICustomerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	private ICustomerDAO customerDAO;

	@Override
	public BankCustomerResponse addCustomer(BankCustomerRequest request) throws SmartBankCustomerException {
		LOGGER.info("In addCustomer method to add new customer");
		Customer customer = new Customer();
		if(request != null) {
			mapRequestToCustomerEntity(request);
			customerDAO.addCustomer(customer);
		} else {
			throw new SmartBankCustomerException(MessageCodeEnum.INVALID_REQUEST, "Request data is not available to process the request. Please provide valid inputs to continue.");
		}

		return mapCustomerEntityToResponse(customer);
	}

	private BankCustomerResponse mapCustomerEntityToResponse(Customer customer) {
		BankCustomerResponse response = new BankCustomerResponse();
		List<BankCustomerAccount> accounts = new ArrayList<>();
		response.setFirstName(customer.getFirstName());
		response.setLastName(customer.getLastName());
		response.setTitle(customer.getTitle());
		response.setEmailId(customer.getEmailId());
		response.setMobileNumber(customer.getMobileNumber());
		response.setNationalId(customer.getNationalId());

		for (CustomerAccount account : customer.getCustomerAccounts()) {
			final BankCustomerAccount responseAccount = new BankCustomerAccount();
			responseAccount.setAccountNumber(String.valueOf(account.getAccountNumber()));
			responseAccount.setAccountType(account.getAccountType());
			accounts.add(responseAccount);
		}

		response.setAccounts(accounts);
		return response;
	}

	private Customer mapRequestToCustomerEntity(BankCustomerRequest pRegistrationRquest) throws SmartBankCustomerException {
		Customer customer = new Customer();

		if (pRegistrationRquest.getFirstName() != null) {
			customer.setFirstName(pRegistrationRquest.getFirstName());
		}
		if (pRegistrationRquest.getLastName() != null) {
			customer.setLastName(pRegistrationRquest.getLastName());
		}
		if (pRegistrationRquest.getEmailId() != null) {
			customer.setEmailId(pRegistrationRquest.getEmailId());
		}
		if (pRegistrationRquest.getMobileNumber() != null) {
			customer.setMobileNumber(pRegistrationRquest.getMobileNumber());
		}
		if (pRegistrationRquest.getNationalId() != null) {
			customer.setNationalId(pRegistrationRquest.getNationalId());
		}
		if (pRegistrationRquest.getCustomerLoginId() != null) {
			customer.setLoginId(pRegistrationRquest.getCustomerLoginId());
		}
		if (pRegistrationRquest.getLoginPassword() != null) {
			customer.setLoginPassword(pRegistrationRquest.getLoginPassword());
		}
		if (pRegistrationRquest.getTitle() != null) {
			customer.setTitle(pRegistrationRquest.getTitle());
		}
		
		addCustomerDependencies(pRegistrationRquest, customer);

		return customer;
	}

	/**
	 * @param pRegistrationRquest
	 * @param pCustomer
	 */
	private void addCustomerDependencies(BankCustomerRequest pRegistrationRquest, Customer pCustomer) {
		if (pRegistrationRquest.getAddress() != null && !pRegistrationRquest.getAddress().isEmpty()) {
			List<CustomerAddress> addresses = new ArrayList<>();

			for (BankCustomerAddress address : pRegistrationRquest.getAddress()) {
				final CustomerAddress entityAddress = new CustomerAddress();
				entityAddress.setAddressLine1(address.getAddressLine1());
				entityAddress.setAddressLine2(address.getAddressLine2());
				entityAddress.setAddressType(address.getAddressType());
				entityAddress.setCity(address.getCity());
				entityAddress.setCountry(address.getCountry());
				entityAddress.setPostcode(address.getPostCode());
				addresses.add(entityAddress);
			}

			pCustomer.setCustomerAddresses(addresses);
		}
		
		if (pRegistrationRquest.getAccounts() != null && !pRegistrationRquest.getAccounts().isEmpty()) {
			List<CustomerAccount> accounts = new ArrayList<>();

			for (BankCustomerAccount registrationAccount : pRegistrationRquest.getAccounts()) {
				final CustomerAccount account = new CustomerAccount();
				account.setAccountType(registrationAccount.getAccountType());
				if(registrationAccount.getAccountNumber() != null && !registrationAccount.getAccountNumber().isEmpty()) {
					account.setAccountNumber(Integer.valueOf(registrationAccount.getAccountNumber()));
				}
				accounts.add(account);
			}

			pCustomer.setCustomerAccounts(accounts);
		}
	}

	@Override
	public BankCustomerResponse findCustomerByAccountNumber(Integer pCustomerAccountNumber)
			throws SmartBankCustomerException {
		LOGGER.info("In findCustomerByAccountNumber method to retrieve the customer");
		
		return mapCustomerEntityToResponse(customerDAO.findCustomerByAccountNumber(pCustomerAccountNumber));
	}
	
	@Override
	public BankCustomerResponse updateCustomer(BankCustomerRequest request) throws SmartBankCustomerException {
		LOGGER.info("In updateCustomer method to update existing customer details");
		
		return mapCustomerEntityToResponse(customerDAO.updateCustomer(mapRequestToCustomerEntity(request)));
	}
}